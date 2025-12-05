package com.cs1530.coursereview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cs1530.coursereview.model.Review;
import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.repository.ReviewRepository;
import com.cs1530.coursereview.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, ReviewRepository reviewRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.reviewRepository = reviewRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student registerStudent(String name, String password) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(passwordEncoder.encode(password));
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

    public List<Review> getStudentReviewHistory(Long userID) {
        return reviewRepository.findByWriterUserID(userID);
    }

    public boolean authenticateStudent(String name, String password) {
        return studentRepository.findByName(name)
                .map(student -> student.login(password, passwordEncoder))
                .orElse(false);
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }
}
