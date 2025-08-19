package com.neki.sistemaskill.service;

import com.neki.sistemaskill.model.Skill;
import com.neki.sistemaskill.model.User;
import com.neki.sistemaskill.model.UserSkill;
import com.neki.sistemaskill.repository.SkillRepository;
import com.neki.sistemaskill.repository.UserRepository;
import com.neki.sistemaskill.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSkillService {
    // Repositório de associações entre usuário e skill
    @Autowired
    private UserSkillRepository userSkillRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillRepository skillRepository;

    // Serviço responsável pela lógica de negócio relacionada às habilidades do usuário

    public List<UserSkill> listUserSkills(Integer userId) {
    // Retorna todas as skills associadas ao usuário
        return userSkillRepository.findByUserId(userId);
    }

    public UserSkill associateSkill(Integer userId, Integer skillId, String level, String descricao) {
    // Associa uma skill ao usuário, se não existir
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new RuntimeException("Skill não encontrada"));
        Optional<UserSkill> existing = userSkillRepository.findByUserId(userId).stream().filter(us -> us.getSkill().getId().equals(skillId)).findFirst();
        if (existing.isPresent()) {
            throw new RuntimeException("Skill já associada ao usuário");
        }
        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill.setLevel(level);
        userSkill.setDescricao(descricao);
        return userSkillRepository.save(userSkill);
    }

    public UserSkill updateUserSkill(Integer userSkillId, String level) {
    // Atualiza o nível de uma skill do usuário
        UserSkill userSkill = userSkillRepository.findById(userSkillId).orElseThrow(() -> new RuntimeException("Associação não encontrada"));
        userSkill.setLevel(level);
        return userSkillRepository.save(userSkill);
    }

    public void deleteUserSkill(Integer userSkillId) {
    // Remove a associação entre usuário e skill
        userSkillRepository.deleteById(userSkillId);
    }

    public User getUserById(Integer userId) {
    // Busca usuário pelo id
        return userRepository.findById(userId).orElse(null);
    }
}
