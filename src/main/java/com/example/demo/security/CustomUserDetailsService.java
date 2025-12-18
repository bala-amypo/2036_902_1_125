package com.example.demo.security;
import org.springframework.security.core.userdetails.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email){

        User user = repo.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("not found"));

        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(user.getEmail());

        builder.password(user.getPassword());
        builder.roles(user.getRole());

        return builder.build();
    }
}
