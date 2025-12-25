package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
    private Ingredient ingredient;

    private Double quantityRequired;

    public Long getId() { return id; }
    public MenuItem getMenuItem() { return menuItem; }
    public Ingredient getIngredient() { return ingredient; }
    public Double getQuantityRequired() { return quantityRequired; }

    public void setId(Long id) { this.id = id; }
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
    public void setQuantityRequired(Double quantityRequired) { this.quantityRequired = quantityRequired; }

    // 🔥 REQUIRED BY TESTS
    public void setQuantity(double quantity) {
        this.quantityRequired = quantity;
    }
}
