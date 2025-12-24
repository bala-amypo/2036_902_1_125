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
    public RecipeIngredient add(@RequestParam Long menuItemId,
                                @RequestParam Long ingredientId,
                                @RequestParam Double quantity) {
        return service.addIngredientToRecipe(menuItemId, ingredientId, quantity);
    }

    @PutMapping("/{id}")
    public RecipeIngredient update(@PathVariable Long id,
                                   @RequestParam Double quantity) {
        return service.updateRecipeIngredient(id, quantity);
    }

    @GetMapping("/menu-item/{menuItemId}")
    public List<RecipeIngredient> list(@PathVariable Long menuItemId) {
        return service.getIngredientsByMenuItem(menuItemId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeIngredientFromRecipe(id);
    }

    @GetMapping("/ingredient/{ingredientId}/total-quantity")
    public Double total(@PathVariable Long ingredientId) {
        return service.getTotalQuantityOfIngredient(ingredientId);
    }
}
