package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {

    // POST
    RecipeIngredient addIngredient(RecipeIngredient recipeIngredient);

    // PUT
    RecipeIngredient updateIngredient(Long id, RecipeIngredient recipeIngredient);

    // DELETE
    void removeIngredient(Long id);

    // GET by menu item
    List<RecipeIngredient> getIngredientsForMenuItem(Long menuItemId);

    // ✅ REQUIRED BY TESTS
    double getTotalQuantityUsed(Long ingredientId);
}
