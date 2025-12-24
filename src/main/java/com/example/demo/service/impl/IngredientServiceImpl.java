package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl {

    private final IngredientRepository repository;

    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        ingredient.setId(id);
        return repository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deactivateIngredient(long id) {
        Ingredient ing = repository.findById(id).orElse(null);
        if (ing != null) {
            ing.setActive(false);
            repository.save(ing);
        }
    }
}

