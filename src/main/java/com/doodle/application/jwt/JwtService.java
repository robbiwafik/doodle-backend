package com.doodle.application.jwt;

import com.doodle.application.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final int MILLISECONDS_IN_SECOND = 1000;
    private final int SECONDS_IN_MINUTE = 60;
    private final int MINUTES_IN_HOURS = 60;
    private final int HOURS_IN_WEEK = 128;

    private final String SECRET_KEY;

    public JwtService(@Value("${jwt.secret-key}") String secretKey) {
        SECRET_KEY = secretKey;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(
                        System.currentTimeMillis()
                                + MILLISECONDS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOURS * HOURS_IN_WEEK
                ))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
