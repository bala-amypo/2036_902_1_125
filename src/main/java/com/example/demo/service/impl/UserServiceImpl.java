package com.example.demo.service.impl;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.util.PasswordEncoderUtil;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final JwtTokenProvider jwtProvider;

    public UserServiceImpl(UserRepository repo, JwtTokenProvider jwtProvider){
        this.repo = repo;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String register(RegisterRequest req){

        if (repo.existsByEmail(req.email)){
            throw new BadRequestException("Email already in use");
        }

        User user = new User();
        user.setFullName(req.fullName);
        user.setEmail(req.email);
        user.setPassword(PasswordEncoderUtil.encode(req.password));
        user.setRole(req.role == null ? "MANAGER" : req.role);

        repo.save(user);

        return jwtProvider.generateToken(req.email);
    }

    @Override
    public String login(AuthRequest req){

        User user = repo.findAll().stream()
                .filter(u -> u.getEmail().equals(req.email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (!PasswordEncoderUtil.matches(req.password, user.getPassword())){
            throw new BadRequestException("Invalid credentials");
        }

        return jwtProvider.generateToken(req.email);
    }
}
