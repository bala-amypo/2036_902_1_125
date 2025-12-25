package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository repo;
    private final MenuItemRepository menuItemRepo;
    private final IngredientRepository ingredientRepo;

    public RecipeIngredientServiceImpl(
            RecipeIngredientRepository repo,
            MenuItemRepository menuItemRepo,
            IngredientRepository ingredientRepo) {

        this.repo = repo;
        this.menuItemRepo = menuItemRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(
            Long menuItemId,
            Long ingredientId,
            Double quantity) {

        MenuItem menuItem = menuItemRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        Ingredient ingredient = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        RecipeIngredient ri = new RecipeIngredient();
        ri.setMenuItem(menuItem);
        ri.setIngredient(ingredient);
        ri.setQuantityRequired(quantity);

        return repo.save(ri);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        RecipeIngredient ri = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe ingredient not found"));

        ri.setQuantityRequired(quantity);
        return repo.save(ri);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return repo.findByMenuItemId(menuItemId);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        repo.deleteById(id);
    }

    // ✅ EXACT MATCH with interface (primitive long)
    @Override
    public Double getTotalQuantityOfIngredient(long ingredientId) {
        return repo.getTotalQuantityByIngredientId(ingredientId);
    }
}
