package com.neki.sistemaskill.repository;

import com.neki.sistemaskill.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repositório para operações de persistência da entidade User
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
}
