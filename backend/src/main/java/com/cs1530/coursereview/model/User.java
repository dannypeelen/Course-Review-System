package com.cs1530.coursereview.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String password;
    private String name;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean login(String rawPassword, PasswordEncoder passwordEncoder) {
        return this.password != null && passwordEncoder.matches(rawPassword, this.password);
    }

    public boolean login(String password) {
        return this.password != null && this.password.equals(password);
    }
}
