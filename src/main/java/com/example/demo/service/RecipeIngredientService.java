package com.example.demo.service;

import com.example.demo.entity.RecipeIngredient;
import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient add(Long menuItemId, Long ingredientId, Double qty);

    RecipeIngredient update(Long id, Double qty);

    List<RecipeIngredient> getRecipeForMenu(Long menuItemId);

    void delete(Long id);
}
