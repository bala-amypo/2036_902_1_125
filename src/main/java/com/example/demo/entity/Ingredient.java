package com.example.demo.entity;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String unit;

    // ✅ CHANGE HERE
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costPerUnit;

    private Boolean active = true;

    public Ingredient() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public Boolean getActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }
    public void setActive(Boolean active) { this.active = active; }
}
