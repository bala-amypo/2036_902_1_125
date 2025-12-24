package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl {

    private final MenuItemRepository repository;

    public MenuItemServiceImpl(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem createMenuItem(MenuItem item) {
        return repository.save(item);
    }

    public MenuItem updateMenuItem(Long id, MenuItem item) {
        item.setId(id);
        return repository.save(item);
    }

    public MenuItem getMenuItemById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public void deactivateMenuItem(Long id) {
        MenuItem item = repository.findById(id).orElse(null);
        if (item != null) {
            item.setActive(false);
            repository.save(item);
        }
    }
}
