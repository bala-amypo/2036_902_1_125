package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return service.createIngredient(ingredient);
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return service.getAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable long id) {
        return service.getIngredientById(id);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable long id,
                                       @RequestBody Ingredient ingredient) {
        return service.updateIngredient(id, ingredient);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateIngredient(@PathVariable long id) {
        service.deactivateIngredient(id);
    }
}
