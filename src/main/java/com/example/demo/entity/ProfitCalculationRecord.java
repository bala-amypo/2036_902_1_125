package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class ProfitCalculationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    private BigDecimal totalCost;
    private BigDecimal profitMargin;
    private Timestamp calculatedAt;

    @PrePersist
    void calcTime() {
        calculatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
