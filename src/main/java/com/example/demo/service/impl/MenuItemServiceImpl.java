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

    private final MenuItemRepository menuRepository;

    public MenuItemServiceImpl(MenuItemRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {

        if (menuItem.getSellingPrice() == null
                || menuItem.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Cost per unit");
        }

        menuItem.setActive(true);

        return menuRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long id, MenuItem updated) {

        MenuItem item = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (updated.getSellingPrice() == null
                || updated.getSellingPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Cost per unit");
        }

        item.setName(updated.getName());
        item.setSellingPrice(updated.getSellingPrice());
        item.setDescription(updated.getDescription());
        item.setCategories(updated.getCategories());

        return menuRepository.save(item);
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    @Override
    public void deactivateMenuItem(Long id) {

        MenuItem item = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        item.setActive(false);

        menuRepository.save(item);
    }
}
