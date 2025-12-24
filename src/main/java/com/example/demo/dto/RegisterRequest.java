package com.example.demo.dto;

public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String role;

    public RegisterRequest() {}

    // getters & setters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
