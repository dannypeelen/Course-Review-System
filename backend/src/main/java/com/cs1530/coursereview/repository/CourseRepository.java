package com.yourorg.coursereview.repository;

import com.yourorg.coursereview.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    // Spring automatically implements these based on method names!
    List<Course> findByTeacherId(Long teacherId);
    Course findByCourseNumber(String courseNumber);
    List<Course> findByTitleContainingIgnoreCase(String keyword);
}
