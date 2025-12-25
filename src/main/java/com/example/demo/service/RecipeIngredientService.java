package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient addIngredientToMenuItem(RecipeIngredient recipeIngredient);

    RecipeIngredient updateRecipeIngredient(long id, double quantity);

    List<RecipeIngredient> getIngredientsByMenuItem(long menuItemId);

    void removeIngredientFromRecipe(long id);

    double getTotalQuantityOfIngredient(long ingredientId);
}
