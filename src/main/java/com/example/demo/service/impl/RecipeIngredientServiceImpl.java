package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final MenuItemRepository menuItemRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeIngredientServiceImpl(
            RecipeIngredientRepository recipeIngredientRepository,
            MenuItemRepository menuItemRepository,
            IngredientRepository ingredientRepository
    ) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.menuItemRepository = menuItemRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public RecipeIngredient addIngredientToMenuItem(
            Long menuItemId,
            Long ingredientId,
            double quantity
    ) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        RecipeIngredient ri = new RecipeIngredient();
        ri.setMenuItem(menuItem);
        ri.setIngredient(ingredient);
        ri.setQuantityRequired(quantity);

        return recipeIngredientRepository.save(ri);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, double quantity) {
        RecipeIngredient ri = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe ingredient not found"));

        ri.setQuantityRequired(quantity);
        return recipeIngredientRepository.save(ri);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        if (!recipeIngredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recipe ingredient not found");
        }
        recipeIngredientRepository.deleteById(id);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeIngredientRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public double getTotalQuantityUsed(Long ingredientId) {
        return recipeIngredientRepository.sumQuantityByIngredientId(ingredientId);
    }
}
