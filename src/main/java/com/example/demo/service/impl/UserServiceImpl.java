package com.example.demo.service.impl;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    public User register(User user) {
        return user;
    }

    public String login(String email, String password) {
        return "dummy-token";
    }
}
