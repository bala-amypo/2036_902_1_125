package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String role = "MANAGER";

    private Boolean active = true;
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public User() {}

    // getters & setters
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}
