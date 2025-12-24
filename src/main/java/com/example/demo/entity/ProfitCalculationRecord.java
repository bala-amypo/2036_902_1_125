package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ProfitCalculationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private MenuItem menuItem;

    private BigDecimal totalCost;
    private BigDecimal profitMargin;

    private LocalDateTime calculatedAt;

    @PrePersist
    void onCreate() {
        calculatedAt = LocalDateTime.now();
    }

    public ProfitCalculationRecord() {}

    // getters & setters
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    public void setProfitMargin(BigDecimal profitMargin) { this.profitMargin = profitMargin; }
}
