package com.neki.sistemaskill.service;

import com.neki.sistemaskill.model.User;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Value("${jwt.secret:mySecretKey}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400000}") // 1 dia em ms
    private long jwtExpiration;


    public Map<String, Object> loginWithUserId(String login, String password) {
        User user = userService.findByEmailOrNickname(login);
        if (user == null || !userService.checkPassword(password, user.getPassword())) {
            throw new RuntimeException("Login, e-mail ou senha inválidos");
        }
        String token = generateToken(user);
        return Map.of(
            "token", token,
            "userId", user.getId(),
            "nickname", user.getNickname()
        );
    }

    private String generateToken(User user) {
    return Jwts.builder()
        .setSubject(user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }
}
