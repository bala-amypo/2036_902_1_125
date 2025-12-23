package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal sellingPrice;
    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToMany
    @JoinTable(
        name = "menu_item_categories",
        joinColumns = @JoinColumn(name = "menu_item_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @PrePersist
    void create() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    void update() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
