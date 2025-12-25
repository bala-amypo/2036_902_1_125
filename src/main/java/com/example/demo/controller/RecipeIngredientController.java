package com.example.demo.controller;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.service.RecipeIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeIngredientController {

    private final RecipeIngredientService service;

    public RecipeIngredientController(RecipeIngredientService service) {
        this.service = service;
    }

    // ✅ CONTROLLER STYLE (Swagger)
    @PostMapping("/menu-item/{menuItemId}/ingredient/{ingredientId}")
    public ResponseEntity<RecipeIngredient> addIngredientToRecipe(
            @PathVariable Long menuItemId,
            @PathVariable Long ingredientId,
            @RequestParam Double quantity) {

        return ResponseEntity.ok(
                service.addIngredientToRecipe(menuItemId, ingredientId, quantity)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeIngredient> updateRecipeIngredient(
            @PathVariable long id,
            @RequestParam double quantity) {

        return ResponseEntity.ok(
                service.updateRecipeIngredient(id, quantity)
        );
    }

    @GetMapping("/menu-item/{menuItemId}")
    public ResponseEntity<List<RecipeIngredient>> getIngredientsByMenuItem(
            @PathVariable long menuItemId) {

        return ResponseEntity.ok(
                service.getIngredientsByMenuItem(menuItemId)
        );
    }

    @DeleteMapping("/{id}")
    public void removeIngredientFromRecipe(@PathVariable long id) {
        service.removeIngredientFromRecipe(id);
    }

    @GetMapping("/ingredient/{ingredientId}/total")
    public ResponseEntity<Double> getTotalQuantity(
            @PathVariable long ingredientId) {

        return ResponseEntity.ok(
                service.getTotalQuantityOfIngredient(ingredientId)
        );
    }
}
