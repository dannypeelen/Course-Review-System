import { Component } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Course } from '../../services/course.service';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.scss']
})
export class CourseDetailComponent {

  constructor(
    private route: ActivatedRoute,
    private courseService: Course) {}
  // Component logic goes here
  // Hard-coded for now
  courses: Course[] = [];
  course: any;
  showAllReviews = false;
  showReviewForm = false;
  reviewText = '';
  maxCharacters = 1000;
  
  reviews = [
    {
      date: '2 weeks ago',
      text: 'Excellent course! The professor explains concepts clearly and the projects are challenging but rewarding.'
    },
    {
      date: '1 month ago',
      text: 'Great introduction to CS. The workload is manageable and the TAs are very helpful.'
    },
    {
      date: '2 months ago',
      text: 'Highly recommend! Perfect for beginners with clear explanations and practical assignments.'
    },
    {
      date: '2 months ago',
      text: 'The course material is well-organized and the professor is engaging. Assignments help reinforce the concepts taught in class.'
    },
    {
      date: '3 months ago',
      text: 'Good balance between theory and practice. The coding assignments are relevant and prepare you well for real-world scenarios.'
    },
    {
      date: '3 months ago',
      text: 'Very informative course with a supportive learning environment. Office hours are helpful for getting extra assistance.'
    },
    {
      date: '4 months ago',
      text: 'Solid course overall. The lectures are comprehensive and the labs give you hands-on experience with the concepts.'
    }
  ];

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.courseService.getCourses().subscribe(courses => {
      this.courses = courses;
      const id = Number(this.route.snapshot.paramMap.get('id'));
      console.log(id);
      this.course = this.courses.find(c => c.id === id);
      console.log(this.course);
    });
  }


  toggleReviews() {
    this.showAllReviews = !this.showAllReviews;
  }

  toggleReviewForm() {
    this.showReviewForm = !this.showReviewForm;
    if (!this.showReviewForm) {
      this.reviewText = '';
    }
  }

  get remainingCharacters(): number {
    return this.maxCharacters - this.reviewText.length;
  }

  submitReview() {
    if (this.reviewText.trim().length > 0 && this.reviewText.length <= this.maxCharacters) {
      const newReview = {
        date: 'Just now',
        text: this.reviewText.trim()
      };
      this.reviews.unshift(newReview);
      this.reviewText = '';
      this.showReviewForm = false;
    }
  }
}