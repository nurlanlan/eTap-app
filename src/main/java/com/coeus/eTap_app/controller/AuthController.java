package com.coeus.eTap_app.controller;

import com.coeus.eTap_app.service.CompanyService;
import com.coeus.eTap_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final CompanyService companyService;

    public AuthController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
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

    @PostMapping("/company/register")
    public String registerCompany(
            @RequestParam String companyEmail,
            @RequestParam String companyPassword,
            @RequestParam String companyName) {
        try {
            companyService.registerCompany(companyEmail, companyPassword, companyName);
            return "Company registered successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("company/login")
    public String loginCompany(
            @RequestParam String companyEmail,
            @RequestParam String companyPassword) {
        try {
            companyService.loginCompany(companyEmail, companyPassword);
            return "Company logged in successfully";
        }catch (Exception e) {
            return e.getMessage();
        }
    }

}
