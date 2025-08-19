package com.neki.sistemaskill.repository;

import com.neki.sistemaskill.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositório para operações de persistência da entidade Skill
public interface SkillRepository extends JpaRepository<Skill, Integer> {
	List<Skill> findByCategoriaId(Integer categoriaId);
}
