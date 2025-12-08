package com.cs1530.coursereview.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {

    // Reviews are now linked via courseId in the reviews table, not via a bidirectional relationship

    public void submitReview(Review review) {
        // Reviews are now linked via courseId field, not writer object
    }

    public Course viewCourseDetails() {
        return null;
    }

    public Review viewCourseReview() {
        return null;
    }

    public List<Review> accountHistory() {
        return new ArrayList<>();
    }

    public Report issueReport(Review review, String content) {
        Report r = new Report();
        r.setReportReview(review);
        r.setContent(content);
        r.setConfirmed(false);
        return r;
    }
}
