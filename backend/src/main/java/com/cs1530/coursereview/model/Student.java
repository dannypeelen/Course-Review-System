package com.cs1530.coursereview.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {

    @OneToMany(mappedBy = "writer")
    private List<Review> reviews = new ArrayList<>();

    public void submitReview(Review review) {
        review.setWriter(this);
        this.reviews.add(review);
    }

    public Course viewCourseDetails() {
        return null;
    }

    public Review viewCourseReview() {
        return null;
    }

    public List<Review> accountHistory() {
        return reviews;
    }

    public Report issueReport(Review review, String content) {
        Report r = new Report();
        r.setReportReview(review);
        r.setContent(content);
        r.setConfirmed(false);
        return r;
    }
}
