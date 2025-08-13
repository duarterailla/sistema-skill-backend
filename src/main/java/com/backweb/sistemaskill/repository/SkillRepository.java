package com.backweb.sistemaskill.repository;

import com.backweb.sistemaskill.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	List<Skill> findByCategoriaId(Integer categoriaId);
}
