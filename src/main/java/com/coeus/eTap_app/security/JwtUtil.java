package com.coeus.eTap_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import jakarta.annotation.PostConstruct;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKeyString;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        if (secretKeyString == null || secretKeyString.isEmpty()) {
            throw new IllegalArgumentException("JWT secret key must be configured properly.");
        }
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String emailFromToken = extractEmail(token);
            final String roleFromToken = extractRole(token);

            // Check if token is expired first
            if (isTokenExpired(token)) {
                return false;
            }

            // Validate email matches
            if (!emailFromToken.equals(userDetails.getUsername())) {
                return false;
            }

            // Validate role matches
            return userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(authority -> authority.equals("ROLE_" + roleFromToken));

        } catch (Exception e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}