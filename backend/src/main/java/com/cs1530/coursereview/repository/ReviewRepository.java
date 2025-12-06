package com.cs1530.coursereview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs1530.coursereview.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByCourseId(Integer courseId);
}
