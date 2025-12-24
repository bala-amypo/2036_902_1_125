package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredients", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String unit;

    @Column(nullable = false)
    private BigDecimal costPerUnit;

    private Boolean active = true;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public Boolean getActive() { return active; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }
    public void setActive(Boolean active) { this.active = active; }
}
