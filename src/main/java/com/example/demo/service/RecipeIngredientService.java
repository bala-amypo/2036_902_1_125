package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient addIngredientToMenuItem(Long menuItemId, Long ingredientId, double quantity);

    default RecipeIngredient addIngredientToMenuItem(RecipeIngredient recipeIngredient) {
        return addIngredientToMenuItem(
                recipeIngredient.getMenuItem().getId(),
                recipeIngredient.getIngredient().getId(),
                recipeIngredient.getQuantityRequired()
        );
    }

    RecipeIngredient updateRecipeIngredient(Long id, double quantity);

    void removeIngredientFromRecipe(Long id);

    List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId);

    double getTotalQuantityOfIngredient(long ingredientId);

    default double getTotalQuantityUsed(Long ingredientId) {
        return getTotalQuantityOfIngredient(ingredientId);
    }
}
