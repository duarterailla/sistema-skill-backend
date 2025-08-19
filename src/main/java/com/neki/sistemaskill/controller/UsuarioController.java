package com.neki.sistemaskill.controller;


import com.neki.sistemaskill.model.User;
import com.neki.sistemaskill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        return userRepository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @PutMapping("/{userId}/info-pessoais")
        public ResponseEntity<User> updateUserInfo(@PathVariable Integer userId, @RequestBody Map<String, String> body) {
            return userRepository.findById(userId)
                    .map(user -> {
                        if (body.containsKey("email")) user.setEmail(body.get("email"));
                        if (body.containsKey("nickname")) user.setNickname(body.get("nickname"));
                        if (body.containsKey("login")) user.setLogin(body.get("login"));
                        if (body.containsKey("nomeCompleto")) user.setNomeCompleto(body.get("nomeCompleto"));
                        if (body.containsKey("contato")) user.setContato(body.get("contato"));
                        if (body.containsKey("informacoesRelevantes")) user.setInformacoesRelevantes(body.get("informacoesRelevantes"));
                        if (body.containsKey("dataNascimento")) {
                            try {
                                user.setDataNascimento(java.time.LocalDate.parse(body.get("dataNascimento")));
                            } catch (Exception e) {
                                // Ignora formato inválido
                            }
                        }
                        User updated = userRepository.save(user);
                        return ResponseEntity.ok(updated);
                    })
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
            if (!userRepository.existsById(userId)) {
                return ResponseEntity.notFound().build();
            }
            userRepository.deleteById(userId);
            return ResponseEntity.ok().build();
        }
}
