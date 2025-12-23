package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ingredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;

import java.util.List;
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        if (ingredient.getCostPerUnit() == null ||
                ingredient.getCostPerUnit().doubleValue() <= 0) {
            throw new BadRequestException("Cost per unit must be greater than zero");
        }
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        existing.setName(ingredient.getName());
        existing.setUnit(ingredient.getUnit());
        existing.setCostPerUnit(ingredient.getCostPerUnit());

        return ingredientRepository.save(existing);
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public void deactivateIngredient(Long id) {
        Ingredient ingredient = getIngredientById(id);
        ingredient.setActive(false);
        ingredientRepository.save(ingredient);
    }
}
