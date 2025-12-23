package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
    private Ingredient ingredient;

    private Double quantityRequired;

    // getters & setters
}
