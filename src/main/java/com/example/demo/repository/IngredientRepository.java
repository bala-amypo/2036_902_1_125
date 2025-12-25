package com.example.demo.repository;

import com.example.demo.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends MenuItemRepository {

    Optional<Ingredient> findByNameIgnoreCase(String name);
}
