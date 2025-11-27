package com.cs1530.coursereview.controller;

import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.model.Administrator;
import com.cs1530.coursereview.service.StudentService;
import com.cs1530.coursereview.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final StudentService studentService;
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AuthController(StudentService studentService, AdministratorRepository administratorRepository) {
        this.studentService = studentService;
        this.administratorRepository = administratorRepository;
    }

    @PostMapping("/login/student")
    public ResponseEntity<Map<String, Object>> loginStudent(@RequestBody Map<String, String> credentials) {
        try {
            String name = credentials.get("name");
            String password = credentials.get("password");

            boolean authenticated = studentService.authenticateStudent(name, password);

            if (authenticated) {
                Student student = studentService.getStudentByName(name);
                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", true);
                response.put("userId", student.getUserID());
                response.put("name", student.getName());
                response.put("role", "STUDENT");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", false);
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", false);
            response.put("message", "Authentication failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login/admin")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Map<String, String> credentials) {
        try {
            String name = credentials.get("name");
            String password = credentials.get("password");

            Administrator admin = administratorRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Administrator not found"));

            boolean authenticated = admin.login(password);

            if (authenticated) {
                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", true);
                response.put("userId", admin.getUserID());
                response.put("name", admin.getName());
                response.put("role", "ADMINISTRATOR");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("authenticated", false);
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", false);
            response.put("message", "Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
