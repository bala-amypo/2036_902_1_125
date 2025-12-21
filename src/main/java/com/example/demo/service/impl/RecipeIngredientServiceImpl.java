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

    private final RecipeIngredientRepository repo;
    private final IngredientRepository ingredientRepo;
    private final MenuItemRepository menuRepo;

    public RecipeIngredientServiceImpl(
            RecipeIngredientRepository repo,
            IngredientRepository ingredientRepo,
            MenuItemRepository menuRepo
    ){
        this.repo = repo;
        this.ingredientRepo = ingredientRepo;
        this.menuRepo = menuRepo;
    }

    @Override
    public RecipeIngredient addIngredientToRecipe(Long menuItemId, Long ingredientId, Double qty){

        Ingredient ing = ingredientRepo.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        MenuItem item = menuRepo.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        RecipeIngredient r = new RecipeIngredient();
        r.setIngredient(ing);
        r.setMenuItem(item);
        r.setQuantityRequired(qty);

        return repo.save(r);
    }

    @Override
    public RecipeIngredient updateRecipeIngredient(Long id, Double qty){

        RecipeIngredient r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        r.setQuantityRequired(qty);

        return repo.save(r);
    }

    @Override
    public List<RecipeIngredient> getIngredientsByMenuItem(Long menuItemId) {
        return repo.findAll().stream()
                .filter(r -> r.getMenuItem().getId().equals(menuItemId))
                .toList();
    }

    @Override
    public void removeIngredientFromRecipe(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Double getTotalQuantityOfIngredient(Long ingredientId) {
        return repo.findAll().stream()
                .filter(r -> r.getIngredient().getId().equals(ingredientId))
                .mapToDouble(RecipeIngredient::getQuantityRequired)
                .sum();
    }
}
