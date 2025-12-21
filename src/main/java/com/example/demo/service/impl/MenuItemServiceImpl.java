package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repo;

    public MenuItemServiceImpl(MenuItemRepository repo){
        this.repo = repo;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {

        if (menuItem.getSellingPrice() == null ||
                menuItem.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Invalid price");
        }

        menuItem.setActive(true);

        return repo.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem updated) {

        MenuItem item = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        if (updated.getSellingPrice() == null ||
                updated.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Invalid price");
        }

        item.setName(updated.getName());
        item.setDescription(updated.getDescription());
        item.setSellingPrice(updated.getSellingPrice());
        item.setCategories(updated.getCategories());

        return repo.save(item);
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return repo.findAll();
    }

    @Override
    public void deactivateMenuItem(Long id) {

        MenuItem item = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        item.setActive(false);

        repo.save(item);
    }
}
