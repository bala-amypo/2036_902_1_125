package com.example.demo.service;

import com.example.demo.entity.Ingredient;
import java.util.List;

public interface IngredientService {

    Ingredient create(Ingredient ingredient);

    Ingredient update(Long id, Ingredient ingredient);

    Ingredient getById(Long id);

    List<Ingredient> getAll();

    void deactivate(Long id);
}
