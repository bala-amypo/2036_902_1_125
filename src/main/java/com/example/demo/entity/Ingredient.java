package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String unit;
    private BigDecimal costPerUnit;
    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
