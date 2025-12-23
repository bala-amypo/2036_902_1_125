package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // ✅ MUST be at least 32 characters
    private static final String SECRET_KEY =
            "my-super-secret-key-for-jwt-signing-123456";

    private static final long VALIDITY_IN_MS = 60 * 60 * 1000; // 1 hour

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String createToken(String email, String role) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + VALIDITY_IN_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                // ✅ CORRECT WAY (Spring Boot 3 compatible)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
