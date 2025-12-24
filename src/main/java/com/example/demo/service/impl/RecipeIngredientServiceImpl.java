package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.service.RecipeIngredientService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final MenuItemRepository menuItemRepository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository,
                                       IngredientRepository ingredientRepository,
                                       MenuItemRepository menuItemRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public RecipeIngredient addIngredientToMenuItem(RecipeIngredient ri) {

        if (ri.getQuantityRequired() <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }

        Ingredient ingredient = ingredientRepository.findById(ri.getIngredient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        MenuItem menuItem = menuItemRepository.findById(ri.getMenuItem().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        ri.setIngredient(ingredient);
        ri.setMenuItem(menuItem);

        return recipeIngredientRepository.save(ri);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeIngredientRepository.findByMenuItemId(menuItemId);
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        recipeIngredientRepository.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return recipeIngredientRepository.getTotalQuantityByIngredientId(ingredientId);
    }
}
