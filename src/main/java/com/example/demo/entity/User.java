package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String role = "MANAGER";
    private Timestamp createdAt;

    @PrePersist
    void create() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
