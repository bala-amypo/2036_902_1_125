package com.example.demo.controller;

import com.example.demo.dto.RecipeIngredientRequest;
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

    // ✅ POST
    @PostMapping
    public RecipeIngredient create(@RequestBody RecipeIngredientRequest request) {
        return service.addIngredient(
                request.getMenuItemId(),
                request.getIngredientId(),
                request.getQuantity()
        );
    }

    // ✅ PUT
    @PutMapping("/{id}")
    public RecipeIngredient update(
            @PathVariable Long id,
            @RequestParam double quantity
    ) {
        return service.updateIngredient(id, quantity);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeIngredient(id);
    }

    // ✅ GET by menu item
    @GetMapping("/menu-item/{menuItemId}")
    public List<RecipeIngredient> getByMenuItem(@PathVariable Long menuItemId) {
        return service.getIngredientsForMenuItem(menuItemId);
    }

    // ✅ GET total quantity
    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public double getTotalQuantity(@PathVariable Long ingredientId) {
        return service.getTotalQuantityUsed(ingredientId);
    }
}
