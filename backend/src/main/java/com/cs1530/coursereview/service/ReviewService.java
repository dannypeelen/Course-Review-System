package com.cs1530.coursereview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs1530.coursereview.model.Course;
import com.cs1530.coursereview.model.Review;
import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.repository.CourseRepository;
import com.cs1530.coursereview.repository.ReviewRepository;
import com.cs1530.coursereview.repository.StudentRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                        CourseRepository courseRepository,
                        StudentRepository studentRepository) {
        this.reviewRepository = reviewRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Review createReview(Long courseId, Long studentId, String content) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Review review = new Review();
        review.setCourse(course);
        review.setWriter(student);
        review.setReviewContent(content);
        review.setLikes(0);
        review.setDislikes(0);

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByCourse(Long courseId) {
        return reviewRepository.findByCourseId(courseId);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review likeReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        review.setLikes(review.getLikes() + 1);
        return reviewRepository.save(review);
    }

    public Review dislikeReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        review.setDislikes(review.getDislikes() + 1);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<Review> getAllReviews() {
        List<Review> allReviews = reviewRepository.findAll();
        return allReviews;
    }
}
