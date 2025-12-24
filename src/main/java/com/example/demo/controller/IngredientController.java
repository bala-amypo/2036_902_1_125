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

    // ✅ CREATE → 200 OK
    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return service.create(ingredient);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Ingredient> getAll() {
        return service.getAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ SAFE UPDATE (NO NULL OVERWRITE)
    @PutMapping("/{id}")
    public Ingredient update(@PathVariable Long id,
                             @RequestBody Ingredient ingredient) {
        return service.update(id, ingredient);
    }

    // ✅ DEACTIVATE
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}
