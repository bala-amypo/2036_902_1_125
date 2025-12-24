package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // ✅ FIXED: RETURNS 200 OK
    @PostMapping
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem item) {
        MenuItem saved = menuItemService.createMenuItem(item);
        return ResponseEntity.ok(saved);   // 👈 200 OK
    }

    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @GetMapping
    public java.util.List<MenuItem> getAll() {
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
