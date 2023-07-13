package com.microservice.authservice.security;

import com.microservice.authservice.DTO.RequestDto;
import com.microservice.authservice.entities.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    private final RouteValidator routeValidator;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exp}")
    private Long jwtExp;

    public JwtProvider(RouteValidator routeValidator) {
        this.routeValidator = routeValidator;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(AuthUser authUser) throws UnsupportedEncodingException {
        return Jwts
                .builder()
                .setSubject(authUser.getUserName())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .claim("id", authUser.getId())
                .claim("role", authUser.getRole())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validate(String token, RequestDto requestDto) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
        } catch (Exception exception) {
            return false;
        }
        return isAdmin(token) || !routeValidator.isAdmin(requestDto);
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception exception) {
            return "Bad token";
        }
    }


    public boolean isAdmin(String token) {
        return (Jwts.parser().setSigningKey(secret))
                .parseClaimsJwt(token)
                .getBody()
                .get("role")
                .equals("admin");
    }
}
