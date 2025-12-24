package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    // REQUIRED BY TESTS
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItem;
    }
}
