package com.coeus.eTap_app.service;

import com.coeus.eTap_app.model.User;
import com.coeus.eTap_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor-based injection
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    public User register(String email, String password) {
//        if (userRepository.findUserByEmail(email) != null) {
//            throw new RuntimeException("Email already in use");
//        }
//
//
//        User user = new User(email, password);
//        return userRepository.save(user);
//    }

    public User register(String userEmail, String userPassword) {
        if (userRepository.findUserByUserEmail(userEmail) != null) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User(userEmail, userPassword);
        user.setUserEmail(userEmail);
        user.setUserPassword(passwordEncoder.encode(userPassword));
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findUserByUserEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getUserPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }
}

