package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category createCategory(Category category) {
        repo.findByNameIgnoreCase(category.getName())
                .ifPresent(c -> {
                    throw new BadRequestException("Category already exists");
                });
        return repo.save(category);
    }

    @Override
    public Category updateCategory(long id, Category updated) {
        Category existing = getCategoryById(id);

        if (updated.getName() != null)
            existing.setName(updated.getName());

        if (updated.getDescription() != null)
            existing.setDescription(updated.getDescription());

        if (updated.getActive() != null)
            existing.setActive(updated.getActive());

        return repo.save(existing);
    }

    @Override
    public Category getCategoryById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public void deactivateCategory(long id) {
        Category category = getCategoryById(id);
        category.setActive(false);
        repo.save(category);
    }
}
