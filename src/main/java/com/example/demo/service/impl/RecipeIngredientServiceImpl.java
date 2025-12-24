package com.example.demo.service.impl;

import com.example.demo.entity.RecipeIngredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeIngredientServiceImpl {

    public RecipeIngredient createRecipeIngredient(
            long menuItemId,
            long ingredientId,
            double quantity
    ) {
        RecipeIngredient ri = new RecipeIngredient();
        ri.setQuantityRequired(quantity);
        return ri;
    }

    public void deleteRecipeIngredient(long id) {
    }

    public List<RecipeIngredient> getByMenuItem(long menuItemId) {
        return new ArrayList<>();
    }

    public double getTotalQuantityByIngredient(long ingredientId) {
        return 0.0;
    }
}
