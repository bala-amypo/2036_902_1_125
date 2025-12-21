package com.example.demo.service.impl;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.RecipeIngredient;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.IngredientRepository;
import com.example.demo.repository.RecipeIngredientRepository;
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
    public RecipeIngredient add(Long menuItemId, Long ingredientId, Double qty){

        Ingredient ing = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        MenuItem item = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        RecipeIngredient r = new RecipeIngredient();
        r.setIngredient(ing);
        r.setMenuItem(item);
        r.setQuantityRequired(qty);

        return recipeRepo.save(r);
    }

    @Override
    public RecipeIngredient update(Long id, Double qty){

        RecipeIngredient r = recipeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe ingredient not found"));

        r.setQuantityRequired(qty);

        return recipeRepo.save(r);
    }

    @Override
    public List<RecipeIngredient> getRecipeForMenu(Long menuItemId){

        return recipeRepo.findAll().stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .toList();
    }

    @Override
    public void delete(Long id){
        recipeRepo.deleteById(id);
    }
}
