package com.cs1530.coursereview.repository;

import com.cs1530.coursereview.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// DB access for course?
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Spring automatically implements these based on method names!
    List<Course> findByCourseNameContainingIgnoreCase(String keyword);
    Course findByCourseCode(String courseCode);
}
