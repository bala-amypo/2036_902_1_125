package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Category create(@RequestBody Category category) {
        return service.createCategory(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody Category category) {
        return service.updateCategory(id, category);
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @GetMapping("/")
    public List<Category> getAll() {
        return service.getAllCategories();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateCategory(id);
    }
}
