package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category createCategory(Category category) {
        return repository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        return repository.save(category);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deactivateCategory(Long id) {
        Category c = repository.findById(id).orElse(null);
        if (c != null) {
            c.setActive(false);
            repository.save(c);
        }
    }
}
