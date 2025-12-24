package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.impl.IngredientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientServiceImpl service;

    public IngredientController(IngredientServiceImpl service) {
        this.service = service;
    }

    // CREATE ingredient
    @PostMapping("/")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return service.createIngredient(ingredient);
    }

    // UPDATE ingredient
    @PutMapping("/{id}")
    public Ingredient updateIngredient(
            @PathVariable long id,
            @RequestBody Ingredient ingredient
    ) {
        return service.updateIngredient(id, ingredient);
    }

    // GET ingredient by ID
    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable long id) {
        return service.getIngredientById(id);
    }

    // GET all ingredients
    @GetMapping("/")
    public List<Ingredient> getAllIngredients() {
        return service.getAllIngredients();
    }

    // DEACTIVATE ingredient
    @PutMapping("/{id}/deactivate")
    public void deactivateIngredient(@PathVariable long id) {
        service.deactivateIngredient(id);
    }
}
