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

    public Review createReview(Integer courseId, Long studentId, String content, Integer rating, Integer difficulty, Integer timeCommitment, Integer numberOfExams, Integer numberOfProjects) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Review review = new Review();
        review.setCourseId(courseId);
        review.setReview(content);
        review.setRating(rating);
        review.setDifficulty(difficulty);
        review.setTimeCommitment(timeCommitment);
        review.setNumberOfExams(numberOfExams);
        review.setNumberOfProjects(numberOfProjects);

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByCourse(Integer courseId) {
        return reviewRepository.findByCourseId(courseId);
    }

    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review updateReview(Integer reviewId, String content, Integer rating, Integer difficulty, Integer timeCommitment, Integer numberOfExams, Integer numberOfProjects) {
        Review review = getReviewById(reviewId);
        if (content != null) review.setReview(content);
        if (rating != null) review.setRating(rating);
        if (difficulty != null) review.setDifficulty(difficulty);
        if (timeCommitment != null) review.setTimeCommitment(timeCommitment);
        if (numberOfExams != null) review.setNumberOfExams(numberOfExams);
        if (numberOfProjects != null) review.setNumberOfProjects(numberOfProjects);
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<Review> getAllReviews() {
        List<Review> allReviews = reviewRepository.findAll();
        return allReviews;
    }
}
