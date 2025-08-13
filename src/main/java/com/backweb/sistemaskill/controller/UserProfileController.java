package com.backweb.sistemaskill.controller;

import com.backweb.sistemaskill.dto.UserProfileDTO;
import com.backweb.sistemaskill.model.User;
import com.backweb.sistemaskill.model.Skill;
import com.backweb.sistemaskill.repository.UserRepository;
import com.backweb.sistemaskill.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getId());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail() == null || user.getEmail().isBlank() ? "Não informado" : user.getEmail());
    dto.setStatus(" Ativo "); // Ajuste conforme lógica de status
    dto.setDataCadastro(user.getDataCadastro());
    dto.setUltimoAcesso(user.getUltimoAcesso());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/skills/categoria/{categoriaId}")
    public ResponseEntity<List<Skill>> listarPorCategoria(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(skillRepository.findByCategoriaId(categoriaId));
    }
}
