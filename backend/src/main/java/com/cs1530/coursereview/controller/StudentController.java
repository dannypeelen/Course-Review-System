package com.cs1530.coursereview.controller;

import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.model.Review;
import com.cs1530.coursereview.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Map<String, String> payload) {
        try {
            String name = payload.get("name");
            String password = payload.get("password");
            Student student = studentService.registerStudent(name, password);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<Review>> getAccountHistory(@PathVariable Long id) {
        try {
            List<Review> reviews = studentService.getStudentReviewHistory(id);
            return ResponseEntity.ok(reviews);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
