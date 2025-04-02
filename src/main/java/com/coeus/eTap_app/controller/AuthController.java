package com.coeus.eTap_app.controller;

import com.coeus.eTap_app.enums.*;
import com.coeus.eTap_app.model.Vacancy;
import com.coeus.eTap_app.service.CompanyService;
import com.coeus.eTap_app.service.UserService;
import com.coeus.eTap_app.service.VacancyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final CompanyService companyService;
    private final VacancyService vacancyService;

    public AuthController(UserService userService, CompanyService companyService, VacancyService vacancyService) {
        this.userService = userService;
        this.companyService = companyService;
        this.vacancyService = vacancyService;
    }

    @PostMapping("user/register")
    public String register(@RequestParam String userEmail, @RequestParam String userPassword) {
        try {
            userService.register(userEmail, userPassword);
            return "user registered successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("user/login")
    public String login(@RequestParam String userEmail, @RequestParam String userPassword) {
        try {
            return userService.login(userEmail, userPassword);
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
            return companyService.loginCompany(companyEmail, companyPassword);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("company/addVacancy")
    public String addVacancy(String vacancyName,
                             String vacancyDescription,
                             Category category,
                             City city,
                             Education education,
                             Experience experience,
                             WorkSchedule workSchedule,
                             EmploymentType employmentType,
                             int salary) {
        vacancyService.addVacancy(vacancyName, vacancyDescription, category, city, education, experience, workSchedule, employmentType, salary);
        return "Vacancy added successfully";
    }

    @GetMapping("auth/vacancies")
    public List<Vacancy> getVacancies() {
        return vacancyService.showAllVacancies();
    }

}
