package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ================= REGISTER =================
    public String register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());

        // ✅ BCrypt password
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // ✅ Default role ADMIN
        user.setRole(req.getRole() != null ? req.getRole() : "ADMIN");

        userRepository.save(user);

        return "User registered successfully";
    }

    // ================= LOGIN (FIXED) =================
    public String login(AuthRequest req) {

        // 🔍 DEBUG (IMPORTANT)
        System.out.println("LOGIN EMAIL = " + req.getEmail());

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        System.out.println("DB PASSWORD = " + user.getPassword());
        System.out.println("DB ROLE = " + user.getRole());

        // ❌ Password mismatch
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("PASSWORD_MISMATCH");
        }

        // ✅ RETURN JWT TOKEN
        return jwtTokenProvider.createToken(
                user.getEmail(),
                user.getRole()
        );
    }
}
