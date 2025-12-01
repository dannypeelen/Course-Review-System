package com.cs1530.coursereview.dto;

public class AuthResponse {
    private boolean authenticated;
    private String token;
    private Long userId;
    private String name;
    private String role;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(boolean authenticated, String token, Long userId, String name, String role) {
        this.authenticated = authenticated;
        this.token = token;
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public AuthResponse(boolean authenticated, String message) {
        this.authenticated = authenticated;
        this.message = message;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
