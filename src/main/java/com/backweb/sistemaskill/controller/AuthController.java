
package com.backweb.sistemaskill.controller;

import com.backweb.sistemaskill.service.AuthService;
import com.backweb.sistemaskill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String nickname = body.get("nickname");
        String password = body.get("password");

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return ResponseEntity.status(400).body(Map.of("message", "E-mail inválido"));
        }
        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+=-]{8,}$")) {
            return ResponseEntity.status(400).body(Map.of("message", "Senha fraca"));
        }
        if (nickname == null || nickname.length() < 3) {
            return ResponseEntity.status(400).body(Map.of("message", "Nickname deve ter pelo menos 3 caracteres"));
        }
        try {
            userService.register(email, nickname, password);
            return ResponseEntity.status(201).body(Map.of("message", "Usuário cadastrado com sucesso!"));
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("E-mail já cadastrado")) {
                return ResponseEntity.status(409).body(Map.of("message", "E-mail já cadastrado"));
            }
            if (msg != null && msg.contains("Nickname já cadastrado")) {
                return ResponseEntity.status(409).body(Map.of("message", "Nickname já cadastrado"));
            }
            return ResponseEntity.status(400).body(Map.of("message", msg != null ? msg : "Erro ao cadastrar"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String login = body.get("login");
        String password = body.get("password");
        try {
            Map<String, Object> result = authService.loginWithUserId(login, password);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("usuário não encontrado")) {
                return ResponseEntity.status(401).body(Map.of("message", "Usuário não encontrado"));
            }
            if (msg != null && msg.contains("senha inválidos")) {
                return ResponseEntity.status(401).body(Map.of("message", "Senha incorreta"));
            }
            return ResponseEntity.status(401).body(Map.of("message", msg != null ? msg : "Erro ao logar"));
        }
    }
}
