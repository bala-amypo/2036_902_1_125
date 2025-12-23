package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.MenuItem;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final CategoryRepository categoryRepository;

    public MenuItemService(MenuItemRepository menuItemRepository,
                           RecipeIngredientRepository recipeIngredientRepository,
                           CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.categoryRepository = categoryRepository;
    }

    public MenuItem createMenuItem(MenuItem item) {

        if (item.getSellingPrice() == null ||
                item.getSellingPrice().doubleValue() <= 0) {
            throw new RuntimeException("Selling price must be > 0");
        }

        // ✅ FIX: Attach managed Category entities
        Set<Category> managedCategories = new HashSet<>();

        if (item.getCategories() != null) {
            for (Category c : item.getCategories()) {
                Category category = categoryRepository.findById(c.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Category not found"));
                managedCategories.add(category);
            }
        }

        item.setCategories(managedCategories);

        return menuItemRepository.save(item);
    }

    // other methods unchanged
}
