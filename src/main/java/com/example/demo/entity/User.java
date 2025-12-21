package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "MANAGER";

    private Timestamp createdAt;

    @PrePersist
    public void create(){
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
