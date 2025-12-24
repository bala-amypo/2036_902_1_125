package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecipeIngredientService;

import java.util.List;

public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final MenuItemRepository menuItemRepo;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeRepo,
                                       IngredientRepository ingredientRepo,
                                       MenuItemRepository menuItemRepo) {
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity) {
        if (quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }

        MenuItem menuItem = menuItemRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        Ingredient ingredient = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        RecipeIngredient ri = new RecipeIngredient();
        ri.setMenuItem(menuItem);
        ri.setIngredient(ingredient);
        ri.setQuantityRequired(quantity);

        return recipeRepo.save(ri);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity) {
        if (quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }
        RecipeIngredient ri = recipeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe ingredient not found"));
        ri.setQuantityRequired(quantity);
        return recipeRepo.save(ri);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeRepo.findAll()
                .stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .toList();
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return recipeRepo.getTotalQuantityByIngredientId(ingredientId);
    }
}
