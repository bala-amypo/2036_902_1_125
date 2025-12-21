package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service){
        this.service = service;
    }

    @PostMapping("/")
    public Ingredient create(@RequestBody Ingredient ingredient){
        return service.create(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable Long id, @RequestBody Ingredient ingredient){
        return service.update(id, ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient get(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Ingredient> list(){
        return service.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id){
        service.deactivate(id);
    }
}
