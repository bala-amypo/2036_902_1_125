package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@Tag(name = "Menu Items")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @PostMapping("/")
    public MenuItem create(@RequestBody MenuItem item) {
        return service.createMenuItem(item);
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id,
                           @RequestBody MenuItem item) {
        return service.updateMenuItem(id, item);
    }

    @GetMapping("/{id}")
    public MenuItem get(@PathVariable Long id) {
        return service.getMenuItemById(id);
    }

    @GetMapping("/")
    public List<MenuItem> getAll() {
        return service.getAllMenuItems();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateMenuItem(id);
    }
}
