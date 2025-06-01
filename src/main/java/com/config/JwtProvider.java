package com.config;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

public class JwtProvider {

    private static final SecretKey KEY = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth ) {
        String jwt = Jwts.builder()
        .setIssuer("Social Media")
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+864000000))
        .claim("email", auth.getName())
        .signWith(KEY)
        .compact(); 
        return jwt;
                
}

public static String getEmailFromToken(String jwt) {
    jwt = jwt.substring(7);
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(jwt)
            .getBody();

    String email = String.valueOf(claims.get("email"));
    return email;
}
}
