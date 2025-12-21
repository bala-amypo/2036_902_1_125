package com.example.demo.controller;

import com.example.demo.entity.RecipeIngredient;
import com.example.demo.service.RecipeIngredientService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipe-ingredients")
public class RecipeIngredientController {

    private final RecipeIngredientService service;

    public RecipeIngredientController(RecipeIngredientService service){
        this.service = service;
    }

    @PostMapping("/")
    public RecipeIngredient add(
            @RequestParam Long menuItemId,
            @RequestParam Long ingredientId,
            @RequestParam Double qty
    ){
        return service.add(menuItemId, ingredientId, qty);
    }

    @PutMapping("/{id}")
    public RecipeIngredient update(
            @PathVariable Long id,
            @RequestParam Double qty){
        return service.update(id, qty);
    }

    @GetMapping("/menu/{menuItemId}")
    public List<RecipeIngredient> list(@PathVariable Long menuItemId){
        return service.getRecipeForMenu(menuItemId);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id){
        service.delete(id);
    }
}
