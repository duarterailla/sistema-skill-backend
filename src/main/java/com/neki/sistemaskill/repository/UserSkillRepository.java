package com.neki.sistemaskill.repository;

import com.neki.sistemaskill.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repositório para operações de persistência da associação UserSkill
public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
    List<UserSkill> findByUserId(Integer userId);
}
