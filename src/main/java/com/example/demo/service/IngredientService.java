package com.example.demo.service;

import com.example.demo.entity.Ingredient;
import java.util.List;

public interface IngredientService {

    Ingredient create(Ingredient ingredient);

    List<Ingredient> getAll();

    Ingredient getById(Long id);

    Ingredient update(Long id, Ingredient ingredient);

    void deactivate(Long id);
}
