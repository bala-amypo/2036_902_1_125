package com.example.demo.service.impl;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repo;

    public MenuItemServiceImpl(MenuItemRepository repo){
        this.repo = repo;
    }

    @Override
    public MenuItem create(MenuItem item){
        item.setActive(true);
        return repo.save(item);
    }

    @Override
    public MenuItem update(Long id, MenuItem updated){
        MenuItem item = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        item.setName(updated.getName());
        item.setDescription(updated.getDescription());
        item.setSellingPrice(updated.getSellingPrice());
        item.setCategories(updated.getCategories());

        return repo.save(item);
    }

    @Override
    public MenuItem getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    @Override
    public List<MenuItem> getAll(){
        return repo.findAll();
    }

    @Override
    public void deactivate(Long id){
        MenuItem item = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        item.setActive(false);

        repo.save(item);
    }
}
