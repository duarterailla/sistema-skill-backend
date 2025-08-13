
package com.neki.sistemaskill.service;

import com.neki.sistemaskill.model.User;
import com.neki.sistemaskill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User register(String email, String nickname, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new RuntimeException("Nickname já cadastrado");
        }
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setLogin(nickname); // login antigo, manter para compatibilidade
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User findByEmailOrNickname(String login) {
        User user = userRepository.findByEmail(login).orElse(null);
        if (user == null) {
            user = userRepository.findByNickname(login).orElse(null);
        }
        return user;
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
