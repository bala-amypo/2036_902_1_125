package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createCategory(category));
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable long id) {
        return service.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable long id,
                                   @RequestBody Category category) {
        return service.updateCategory(id, category);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateCategory(@PathVariable long id) {
        service.deactivateCategory(id);
    }
}
