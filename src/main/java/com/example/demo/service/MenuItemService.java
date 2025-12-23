package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.MenuItem;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    // ================= CREATE =================
    public MenuItem createMenuItem(MenuItem item) {

        if (item.getSellingPrice() == null ||
                item.getSellingPrice().doubleValue() <= 0) {
            throw new RuntimeException("Selling price must be > 0");
        }

        item.setCategories(getManagedCategories(item.getCategories()));
        return menuItemRepository.save(item);
    }

    // ================= UPDATE =================
    public MenuItem updateMenuItem(Long id, MenuItem updated) {

        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("MenuItem not found"));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setSellingPrice(updated.getSellingPrice());
        existing.setCategories(getManagedCategories(updated.getCategories()));

        return menuItemRepository.save(existing);
    }

    // ================= GET BY ID =================
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("MenuItem not found"));
    }

    // ================= GET ALL =================
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // ================= DEACTIVATE =================
    public void deactivateMenuItem(Long id) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("MenuItem not found"));

        item.setActive(false);
        menuItemRepository.save(item);
    }

    // ================= HELPER (IMPORTANT) =================
    private Set<Category> getManagedCategories(Set<Category> categories) {

        Set<Category> managed = new HashSet<>();

        if (categories != null) {
            for (Category c : categories) {
                Category category = categoryRepository.findById(c.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Category not found"));
                managed.add(category);
            }
        }
        return managed;
    }
}
