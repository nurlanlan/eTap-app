package com.coeus.eTap_app.controller;

import com.coeus.eTap_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String userEmail, @RequestParam String userPassword) {
        try {
            userService.register(userEmail, userPassword);
            return "user registered successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String userEmail, @RequestParam String userPassword) {
        try {
            userService.login(userEmail, userPassword);
            return "User logged in successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello World";
//    }
}
