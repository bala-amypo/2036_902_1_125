package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Boolean active = true;

    @ManyToMany(mappedBy = "categories")
    private Set<MenuItem> menuItems;

    // getters & setters
}
