package com.cs1530.coursereview.controller;

import com.cs1530.coursereview.dto.AuthRequest;
import com.cs1530.coursereview.dto.AuthResponse;
import com.cs1530.coursereview.model.Student;
import com.cs1530.coursereview.model.Administrator;
import com.cs1530.coursereview.security.JwtUtil;
import com.cs1530.coursereview.service.StudentService;
import com.cs1530.coursereview.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final StudentService studentService;
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(StudentService studentService, AdministratorRepository administratorRepository,
                         PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.studentService = studentService;
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login/student")
    public ResponseEntity<AuthResponse> loginStudent(@RequestBody AuthRequest request) {
        try {
            String name = request.getName();
            String password = request.getPassword();

            boolean authenticated = studentService.authenticateStudent(name, password);

            if (authenticated) {
                Student student = studentService.getStudentByName(name);
                String token = jwtUtil.generateToken(name, "STUDENT");

                AuthResponse response = new AuthResponse(true, token, student.getUserID(),
                                                        student.getName(), "STUDENT");
                return ResponseEntity.ok(response);
            } else {
                AuthResponse response = new AuthResponse(false, "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(false, "Authentication failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login/admin")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody AuthRequest request) {
        try {
            String name = request.getName();
            String password = request.getPassword();

            Administrator admin = administratorRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Administrator not found"));

            boolean authenticated = admin.login(password, passwordEncoder);

            if (authenticated) {
                String token = jwtUtil.generateToken(name, "ADMINISTRATOR");

                AuthResponse response = new AuthResponse(true, token, admin.getUserID(),
                                                        admin.getName(), "ADMINISTRATOR");
                return ResponseEntity.ok(response);
            } else {
                AuthResponse response = new AuthResponse(false, "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            AuthResponse response = new AuthResponse(false, "Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
