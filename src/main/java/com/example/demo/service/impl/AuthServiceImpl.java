package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;

    public AuthServiceImpl(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public User register(User user) {

        if(repo.existsByEmail(user.getEmail())){
            throw new BadRequestException("Email already in use");
        }

        if(user.getPassword() == null || user.getPassword().isBlank()){
            throw new BadRequestException("Password required");
        }

        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = repo.findByEmail(email);

        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }

        if(!user.getPassword().equals(password)){
            throw new BadRequestException("Invalid credentials");
        }

        return user;
    }
}
