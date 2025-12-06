package com.cs1530.coursereview.controller;

import com.cs1530.coursereview.model.Review;
import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        List<Review> response = reviewService.getAllReviews();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, Object> payload) {
        try {
            Integer courseId = Integer.valueOf(payload.get("courseId").toString());
            Long studentId = Long.valueOf(payload.get("studentId").toString());
            String content = payload.get("content").toString();
            Integer rating = Integer.valueOf(payload.get("rating").toString());
            Integer difficulty = Integer.valueOf(payload.get("difficulty").toString());
            Integer timeCommitment = Integer.valueOf(payload.get("timeCommitment").toString());
            Integer numberOfExams = Integer.valueOf(payload.get("numberOfExams").toString());
            Integer numberOfProjects = Integer.valueOf(payload.get("numberOfProjects").toString());

            Review review = reviewService.createReview(courseId, studentId, content, rating, difficulty, timeCommitment, numberOfExams, numberOfProjects);
            return ResponseEntity.status(HttpStatus.CREATED).body(review);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Review>> getReviewsByCourse(@PathVariable Integer courseId) {
        List<Review> reviews = reviewService.getReviewsByCourse(courseId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        try {
            Review review = reviewService.getReviewById(id);
            return ResponseEntity.ok(review);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Map<String, Object> payload) {
        try {
            String content = payload.containsKey("content") ? payload.get("content").toString() : null;
            Integer rating = payload.containsKey("rating") ? Integer.valueOf(payload.get("rating").toString()) : null;
            Integer difficulty = payload.containsKey("difficulty") ? Integer.valueOf(payload.get("difficulty").toString()) : null;
            Integer timeCommitment = payload.containsKey("timeCommitment") ? Integer.valueOf(payload.get("timeCommitment").toString()) : null;
            Integer numberOfExams = payload.containsKey("numberOfExams") ? Integer.valueOf(payload.get("numberOfExams").toString()) : null;
            Integer numberOfProjects = payload.containsKey("numberOfProjects") ? Integer.valueOf(payload.get("numberOfProjects").toString()) : null;

            Review review = reviewService.updateReview(id, content, rating, difficulty, timeCommitment, numberOfExams, numberOfProjects);
            return ResponseEntity.ok(review);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
