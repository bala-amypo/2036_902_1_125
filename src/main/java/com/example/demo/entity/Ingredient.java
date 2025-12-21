package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Ingredient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String unit;

    private BigDecimal costPerUnit;

    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    public void onCreate(){
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate(){
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}

