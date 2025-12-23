package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category updated) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setActive(updated.getActive());

        return repository.save(existing);
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public void deactivate(Long id) {
        Category category = getById(id);
        category.setActive(false);
        repository.save(category);
    }
}
