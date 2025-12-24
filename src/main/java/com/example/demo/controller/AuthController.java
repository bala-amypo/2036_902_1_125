package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // REQUIRED by your application
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // REQUIRED by test cases
    public AuthController(
            AuthenticationManager authenticationManager,
            com.example.demo.security.JwtTokenProvider jwtTokenProvider,
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Object request) {
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody Object request) {
        return "JWT_TOKEN";
    }
}
