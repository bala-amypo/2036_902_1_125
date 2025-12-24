package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // 🔐 Secret key (min 32 chars for HS256)
    private static final String SECRET_KEY =
            "menu-profitability-secret-key-1234567890";

    // ⏳ Token validity (24 hours)
    private static final long VALIDITY_IN_MS = 24 * 60 * 60 * 1000;

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generate JWT token
     */
    public String generateToken(Authentication authentication,
                                com.example.demo.entity.User user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getEmail());

        claims.put("role", user.getRole());

        Date now = new Date();
        Date expiry = new Date(now.getTime() + VALIDITY_IN_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract email from token
     */
    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * ✅ REQUIRED: Extract role from token
     */
    public String getRoleFromToken(String token) {
        return (String) parseClaims(token).get("role");
    }

    /**
     * Validate token
     */
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Parse JWT claims
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
