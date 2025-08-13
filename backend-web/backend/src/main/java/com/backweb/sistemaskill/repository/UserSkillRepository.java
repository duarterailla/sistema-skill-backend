package com.backweb.sistemaskill.repository;

import com.backweb.sistemaskill.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
    List<UserSkill> findByUserId(Integer userId);
}
