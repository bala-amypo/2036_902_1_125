package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RecipeIngredientRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.RecipeIngredientService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final MenuItemRepository menuRepo;

    public RecipeIngredientServiceImpl(
            RecipeIngredientRepository recipeRepo,
            IngredientRepository ingredientRepo,
            MenuItemRepository menuRepo
    ){
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.menuRepo = menuRepo;
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double quantity){

        Ingredient ing = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        MenuItem item = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        RecipeIngredient r = new RecipeIngredient();
        r.setIngredient(ing);
        r.setMenuItem(item);
        r.setQuantityRequired(quantity);

        return recipeRepo.save(r);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double quantity){

        RecipeIngredient r = recipeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        r.setQuantityRequired(quantity);

        return recipeRepo.save(r);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return recipeRepo.findAll().stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .toList();
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {

        return recipeRepo.findAll().stream()
                .filter(r -> r.getIngredient().getId().equals(ingredientId))
                .mapToDouble(RecipeIngredient::getQuantityRequired)
                .sum();
    }
}
