package com.example.demo.service.impl;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientServiceImpl {

    private final RecipeIngredientRepository repository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository repository) {
        this.repository = repository;
    }

    public RecipeIngredient save(RecipeIngredient ri) {
        return repository.save(ri);
    }
}
