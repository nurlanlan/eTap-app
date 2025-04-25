package com.coeus.eTap_app.service;

import com.coeus.eTap_app.domain.model.User;
import com.coeus.eTap_app.repository.UserRepository;
import com.coeus.eTap_app.security.JwtUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(String userEmail, String userPassword) {
        if (userRepository.findUserByUserEmail(userEmail) != null) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(userEmail, userPassword);
        user.setUserEmail(userEmail);
        user.setUserPassword(passwordEncoder.encode(userPassword));
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findUserByUserEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getUserPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(email, "USER");
    }
}

