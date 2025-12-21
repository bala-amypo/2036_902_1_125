package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        category.setActive(true);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updated) {

        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        cat.setName(updated.getName());
        cat.setDescription(updated.getDescription());

        return categoryRepository.save(cat);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        category.setActive(false);

        categoryRepository.save(category);
    }
}

