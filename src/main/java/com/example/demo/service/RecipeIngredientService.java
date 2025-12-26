package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient addIngredientToMenuItem(Long menuItemId, Long ingredientId, double quantity);

    RecipeIngredient updateRecipeIngredient(Long id, double quantity);

    void removeIngredientFromRecipe(Long id);

    List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId);

    // REQUIRED BY TESTS
    double getTotalQuantityOfIngredient(long ingredientId);

    // 🔒 COMPATIBILITY FOR CONTROLLER
    default double getTotalQuantityUsed(Long ingredientId) {
        return getTotalQuantityOfIngredient(ingredientId);
    }
}
