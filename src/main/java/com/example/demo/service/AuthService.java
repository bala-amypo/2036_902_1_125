package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthService {

    User register(User user);

    User login(String email, String password);
}
