import { Component } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Course, CourseService } from '../../services/course.service';
import { Review, ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.scss']
})
export class CourseDetailComponent {
  course?: Course;
  reviews: Review[] = [];
  showAllReviews = false;
  showReviewForm = false;
  reviewText = '';
  rating = 5;
  difficulty = 3;
  timeCommitment = 10;
  numberOfExams = 2;
  numberOfProjects = 3;
  maxCharacters = 1000;
  
  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    private reviewService: ReviewService
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCourse(id);
    this.loadReviews(id);
  }

  loadCourse(id: number) {
    this.courseService.getCourseById(id).subscribe({
      next: (course) => {
        this.course = course;
      },
      error: (error) => {
        console.error('Error loading course:', error);
      }
    });
  }

  loadReviews(courseId: number) {
    this.reviewService.getReviewsByCourse(courseId).subscribe({
      next: (reviews) => {
        this.reviews = reviews;
      },
      error: (error) => {
        console.error('Error loading reviews:', error);
      }
    });
  }

  get averageRating(): number {
    if (this.reviews.length === 0) return 0;
    const sum = this.reviews.reduce((acc, r) => acc + (r.rating || 0), 0);
    return Number((sum / this.reviews.length).toFixed(1));
  }

  get averageDifficulty(): number {
    if (this.reviews.length === 0) return 0;
    const sum = this.reviews.reduce((acc, r) => acc + (r.difficulty || 0), 0);
    return Number((sum / this.reviews.length).toFixed(1));
  }

  get averageTimeCommitment(): number {
    if (this.reviews.length === 0) return 0;
    const sum = this.reviews.reduce((acc, r) => acc + (r.timeCommitment || 0), 0);
    return Number((sum / this.reviews.length).toFixed(1));
  }

  get averageExams(): number {
    if (this.reviews.length === 0) return 0;
    const sum = this.reviews.reduce((acc, r) => acc + (r.numberOfExams || 0), 0);
    return Number((sum / this.reviews.length).toFixed(1));
  }

  get averageProjects(): number {
    if (this.reviews.length === 0) return 0;
    const sum = this.reviews.reduce((acc, r) => acc + (r.numberOfProjects || 0), 0);
    return Number((sum / this.reviews.length).toFixed(1));
  }

  toggleReviews() {
    this.showAllReviews = !this.showAllReviews;
  }

  toggleReviewForm() {
    this.showReviewForm = !this.showReviewForm;
    if (!this.showReviewForm) {
      this.reviewText = '';
      this.rating = 5;
      this.difficulty = 3;
      this.timeCommitment = 10;
      this.numberOfExams = 2;
      this.numberOfProjects = 3;
    }
  }

  get remainingCharacters(): number {
    return this.maxCharacters - this.reviewText.length;
  }

  submitReview() {
    if (this.reviewText.trim().length > 0 && this.reviewText.length <= this.maxCharacters && this.course) {
      const newReview = {
        courseId: this.course.courseId,
        studentId: 1, // TODO: Get from logged-in user
        content: this.reviewText.trim(),
        rating: this.rating,
        difficulty: this.difficulty,
        timeCommitment: this.timeCommitment,
        numberOfExams: this.numberOfExams,
        numberOfProjects: this.numberOfProjects
      };
      
      this.reviewService.addReview(newReview).subscribe({
        next: (review) => {
          this.reviews.unshift(review);
          this.reviewText = '';
          this.showReviewForm = false;
        },
        error: (error) => {
          console.error('Error submitting review:', error);
          alert('Failed to submit review. Please try again.');
        }
      });
    }
  }
}