package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem item) {
        return ResponseEntity.ok(menuItemService.createMenuItem(item));
    }

    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @GetMapping
    public List<MenuItem> getAll() {
        return menuItemService.getAllMenuItems();
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id,
                           @RequestBody MenuItem item) {
        return menuItemService.updateMenuItem(id, item);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        menuItemService.deactivateMenuItem(id);
    }
}
