package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo){
        this.repo = repo;
    }

    @Override
    public Category createCategory(Category category) {
        category.setActive(true);
        return repo.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updated) {

        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(updated.getName());
        category.setDescription(updated.getDescription());

        return repo.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {

        Category category = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setActive(false);

        repo.save(category);
    }
}
