package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // ✅ CREATE CATEGORY
    @PostMapping("/")
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }

    // ✅ UPDATE CATEGORY
    @PutMapping("/{id}")
    public Category update(
            @PathVariable Long id,
            @RequestBody Category category
    ) {
        return service.update(id, category);
    }

    // ✅ GET CATEGORY BY ID
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ GET ALL CATEGORIES
    @GetMapping("/")
    public List<Category> getAll() {
        return service.getAll();
    }

    // ✅ SOFT DELETE (DEACTIVATE)
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}
