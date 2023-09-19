package com.doodle.application.jwt;

import com.doodle.application.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final int MILLISECONDS_IN_SECOND = 1000;
    private final int SECONDS_IN_MINUTE = 60;
    private final int MINUTES_IN_HOURS = 60;
    private final int HOURS_IN_WEEK = 128;
    private final String SECRET_KEY;

    public JwtUtil(@Value("${jwt.secret-key}") String secretKey) {
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

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public Date extractExpirationDate(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    public boolean isValidToken(String jwt, User user) {
        String usernameFromJwt = extractUsername(jwt);
        String usernameFromUser = user.getUsername();
        Date currentDate = new Date();
        Date expirationDate = extractExpirationDate(jwt);

        return usernameFromJwt.equals(usernameFromUser) && currentDate.before(expirationDate);
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(jwt);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
