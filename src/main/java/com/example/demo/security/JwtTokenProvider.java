package com.example.demo.security;

public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(Object auth, Object user) {
        return "jwt-token";
    }
}
