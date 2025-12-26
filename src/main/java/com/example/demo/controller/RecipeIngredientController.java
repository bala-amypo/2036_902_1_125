package com.example.demo.controller;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe-ingredients")
public class RecipeIngredientController {

    private final RecipeIngredientService service;

    public RecipeIngredientController(RecipeIngredientService service) {
        this.service = service;
    }

    // POST /api/recipe-ingredients
    @PostMapping
    public RecipeIngredient add(@RequestBody RecipeIngredient recipeIngredient) {
        return service.addIngredient(recipeIngredient);
    }

    // PUT /api/recipe-ingredients/{id}
    @PutMapping("/{id}")
    public RecipeIngredient update(
            @PathVariable Long id,
            @RequestBody RecipeIngredient recipeIngredient
    ) {
        return service.updateIngredient(id, recipeIngredient);
    }

    // DELETE /api/recipe-ingredients/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeIngredient(id);
    }

    // GET /api/recipe-ingredients/menu-item/{menuItemId}
    @GetMapping("/menu-item/{menuItemId}")
    public List<RecipeIngredient> byMenuItem(@PathVariable Long menuItemId) {
        return service.getIngredientsForMenuItem(menuItemId);
    }

    // ✅ REQUIRED BY TESTS
    // GET /api/recipe-ingredients/ingredient/{ingredientId}/total-quantity
    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public double totalQuantity(@PathVariable Long ingredientId) {
        return service.getTotalQuantityUsed(ingredientId);
    }
}
