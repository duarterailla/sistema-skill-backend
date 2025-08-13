package com.neki.sistemaskill.controller;

import com.neki.sistemaskill.model.Categoria;
import com.neki.sistemaskill.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        String nome = body.get("nome");
        if (nome != null) categoria.setNome(nome);
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCategoria(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
