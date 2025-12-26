package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createMenuItem(item));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(service.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable long id) {
        return ResponseEntity.ok(service.getMenuItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable long id,
                                                   @RequestBody MenuItem item) {
        return ResponseEntity.ok(service.updateMenuItem(id, item));
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateMenuItem(@PathVariable long id) {
        service.deactivateMenuItem(id);
    }
}
