package com.neki.sistemaskill.repository;

import com.neki.sistemaskill.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para operações de persistência da entidade Categoria
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
