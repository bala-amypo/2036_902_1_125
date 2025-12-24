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

    // ✅ CREATE (returns 200 OK by default)
    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ UPDATE (partial-safe)
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody Category category) {
        return service.update(id, category);
    }

    // ✅ DEACTIVATE
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}
