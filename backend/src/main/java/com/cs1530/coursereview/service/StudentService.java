package com.cs1530.coursereview.service;

import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.model.Review;
import com.cs1530.coursereview.repository.StudentRepository;
import com.cs1530.coursereview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ReviewRepository reviewRepository) {
        this.studentRepository = studentRepository;
        this.reviewRepository = reviewRepository;
    }

    public Student registerStudent(String name, String password) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(password);
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student getStudentByName(String name) {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Review> getStudentReviewHistory(Long studentId) {
        return reviewRepository.findByWriterId(studentId);
    }

    public boolean authenticateStudent(String name, String password) {
        return studentRepository.findByName(name)
                .map(student -> student.login(password))
                .orElse(false);
    }
}
