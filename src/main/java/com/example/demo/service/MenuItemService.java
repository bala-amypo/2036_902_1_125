package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MenuItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.RecipeIngredientRepository;

import java.util.List;
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
            throw new BadRequestException("Selling price must be greater than zero");
        }
        return menuItemRepository.save(item);
    }

    public MenuItem updateMenuItem(Long id, MenuItem item) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        existing.setName(item.getName());
        existing.setDescription(item.getDescription());
        existing.setSellingPrice(item.getSellingPrice());

        return menuItemRepository.save(existing);
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAllActiveWithCategories();
    }

    public void deactivateMenuItem(Long id) {
        MenuItem item = getMenuItemById(id);
        item.setActive(false);
        menuItemRepository.save(item);
    }
}
