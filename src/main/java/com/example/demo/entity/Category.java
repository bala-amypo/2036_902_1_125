package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private Boolean active = true;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<MenuItem> menuItems = new HashSet<>();

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Boolean getActive() { return active; }
    public Set<MenuItem> getMenuItems() { return menuItems; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(Boolean active) { this.active = active; }
    public void setMenuItems(Set<MenuItem> menuItems) { this.menuItems = menuItems; }
}
