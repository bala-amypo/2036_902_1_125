package com.example.demo.service;

import com.example.demo.entity.Ingredient;
import java.util.List;

public interface IngredientService {

    Ingredient createIngredient(Ingredient ingredient);

    Ingredient updateIngredient(long id, Ingredient ingredient);

    Ingredient getIngredientById(long id);

    List<Ingredient> getAllIngredients();

    void deactivateIngredient(long id);
}
