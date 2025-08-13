package com.neki.sistemaskill.controller;

import com.neki.sistemaskill.model.Skill;
import com.neki.sistemaskill.repository.SkillRepository;
import com.neki.sistemaskill.model.Categoria;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listSkills() {
        List<Skill> skills = skillRepository.findAll();
        List<Map<String, Object>> result = skills.stream().map(skill -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", skill.getId());
            map.put("nome", skill.getNome());
            map.put("descricao", skill.getDescricao());
            map.put("imagem_url", skill.getImagemUrl());
            if (skill.getCategoria() != null) {
                map.put("categoria_id", skill.getCategoria().getId());
                map.put("categoria", java.util.Map.of(
                    "id", skill.getCategoria().getId(),
                    "nome", skill.getCategoria().getNome()
                ));
            } else {
                map.put("categoria_id", null);
                map.put("categoria", null);
            }
            return map;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Skill>> listarPorCategoria(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(skillRepository.findByCategoriaId(categoriaId));
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        String descricao = body.get("descricao");
        String imagemUrl = body.get("imagem_url");
        Skill skill = new Skill();
    skill.setNome(nome);
        skill.setDescricao(descricao);
        skill.setImagemUrl(imagemUrl);
        return ResponseEntity.ok(skillRepository.save(skill));
    }
}
