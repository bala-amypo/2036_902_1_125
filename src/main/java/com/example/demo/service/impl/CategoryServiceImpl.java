package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl {

    public Category createCategory(Category category) {
        category.setActive(true);
        return category;
    }

    public Category updateCategory(long id, Category category) {
        category.setId(id);
        return category;
    }

    public Category getCategoryById(long id) {
        Category category = new Category();
        category.setId(id);
        category.setActive(true);
        return category;
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>();
    }

    public void deactivateCategory(long id) {
    }
}
