package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemServiceImpl {

    public MenuItem createMenuItem(MenuItem menuItem) {
        menuItem.setActive(true);
        return menuItem;
    }

    public MenuItem updateMenuItem(long id, MenuItem menuItem) {
        menuItem.setId(id);
        return menuItem;
    }

    public MenuItem getMenuItemById(long id) {
        MenuItem item = new MenuItem();
        item.setId(id);
        item.setActive(true);
        return item;
    }

    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>();
    }

    public void deactivateMenuItem(long id) {
    }
}
