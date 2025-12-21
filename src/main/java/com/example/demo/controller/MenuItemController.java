package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service){
        this.service = service;
    }

    @PostMapping("/")
    public MenuItem create(@RequestBody MenuItem item){
        return service.create(item);
    }

    @PutMapping("/{id}")
    public MenuItem update(@PathVariable Long id, @RequestBody MenuItem item){
        return service.update(id, item);
    }

    @GetMapping("/{id}")
    public MenuItem get(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/")
    public List<MenuItem> list(){
        return service.getAll();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id){
        service.deactivate(id);
    }
}
