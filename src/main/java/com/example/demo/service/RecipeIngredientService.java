package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient addIngredientToMenuItem(RecipeIngredient recipeIngredient);

    RecipeIngredient updateRecipeIngredient(Long id, Double quantity);

    List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId);
    
    RecipeIngredient addIngredientToMenuItem(RecipeIngredient recipeIngredient);


    void removeIngredientFromRecipe(Long id);

    Double getTotalQuantityOfIngredient(long ingredientId);
}
