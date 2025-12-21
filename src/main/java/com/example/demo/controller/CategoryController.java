package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @PostMapping("/")
    public Category create(@RequestBody Category category){
        return service.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        return service.update(id, category);
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Category> list(){
        return service.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id){
        service.deactivate(id);
    }
}
