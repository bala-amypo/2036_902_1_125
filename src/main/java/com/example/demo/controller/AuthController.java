package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Login & Registration APIs")
@SecurityRequirement(name = "")   // ✅ MAKES AUTH CONTROLLER VISIBLE IN SWAGGER
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201).body(userService.register(request));
    }

    @Operation(summary = "Login and get JWT token")
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userService.login(request);
        String token = jwtTokenProvider.generateToken(auth, user);

        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
