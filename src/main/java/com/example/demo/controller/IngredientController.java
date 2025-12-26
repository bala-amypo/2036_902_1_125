package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createIngredient(ingredient));
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok(service.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id) {
        return ResponseEntity.ok(service.getIngredientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable long id,
                                                       @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(service.updateIngredient(id, ingredient));
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateIngredient(@PathVariable long id) {
        service.deactivateIngredient(id);
    }
}
