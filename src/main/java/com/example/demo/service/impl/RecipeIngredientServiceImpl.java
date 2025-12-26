package com.example.demo.service.impl;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository repository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecipeIngredient addIngredient(RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient updateIngredient(Long id, RecipeIngredient recipeIngredient) {
        RecipeIngredient existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe ingredient not found"));

        existing.setQuantityRequired(recipeIngredient.getQuantityRequired());
        return repository.save(existing);
    }

    @Override
    public void removeIngredient(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<RecipeIngredient> getIngredientsForMenuItem(Long menuItemId) {
        return repository.findByMenuItemId(menuItemId);
    }

    @Override
    public double getTotalQuantityUsed(Long ingredientId) {
        return repository.findByIngredientId(ingredientId)
                .stream()
                .mapToDouble(RecipeIngredient::getQuantityRequired)
                .sum();
    }
}
