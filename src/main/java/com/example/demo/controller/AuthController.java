package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthRequest;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
        return service.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest req){
        return service.login(req);
    }
}
