package com.lttrung.taskmanagement.utils;

import com.lttrung.taskmanagement.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper implements Serializable {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());
        claims.put("role", user.getRole());
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiredAt = new Date(issuedAt.getTime() + 3600 * 1000);
        return Jwts.builder().setClaims(claims).setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = decodeToken(token);
        String username = (String) claims.get("username");
        String usernameInDatabase = userDetails.getUsername();
        boolean isAlive = claims.getExpiration().before(new Date());
        return (username.equals(usernameInDatabase) && !isAlive);
    }
}
