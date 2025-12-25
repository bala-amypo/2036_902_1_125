package com.example.demo.service;

import com.example.demo.entity.Category;
import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category updateCategory(long id, Category category);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    void deactivateCategory(long id);
}
