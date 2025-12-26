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

    @PostMapping
    public RecipeIngredient add(@RequestBody RecipeIngredient ri) {
        return service.addIngredientToMenuItem(
                ri.getMenuItem().getId(),
                ri.getIngredient().getId(),
                ri.getQuantityRequired()
        );
    }

    @PutMapping("/{id}")
    public RecipeIngredient update(
            @PathVariable Long id,
            @RequestParam double quantity
    ) {
        return service.updateRecipeIngredient(id, quantity);
    }

    @GetMapping("/menu-item/{menuItemId}")
    public List<RecipeIngredient> getByMenuItem(@PathVariable Long menuItemId) {
        return service.getIngredientsByMenuItem(menuItemId);
    }

    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public double getTotalQuantity(@PathVariable Long ingredientId) {
        return service.getTotalQuantityOfIngredient(ingredientId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeIngredientFromRecipe(id);
    }
}
