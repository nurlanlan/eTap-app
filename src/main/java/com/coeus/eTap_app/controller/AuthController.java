package com.coeus.eTap_app.controller;

import com.coeus.eTap_app.domain.dto.VacancyDto;
import com.coeus.eTap_app.enums.*;
import com.coeus.eTap_app.domain.model.Vacancy;
import com.coeus.eTap_app.service.CompanyService;
import com.coeus.eTap_app.service.UserService;
import com.coeus.eTap_app.service.VacancyService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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

//    //    @PreAuthorize("hasRole('COMPANY')")
//    @PostMapping("company/addVacancy")
//    public String addVacancy(String vacancyName,
//                             String vacancyDescription,
//                             Category category,
//                             City city,
//                             Education education,
//                             Experience experience,
//                             WorkSchedule workSchedule,
//                             EmploymentType employmentType,
//                             LocalDateTime vacancyAddedDate,
//                             int salary) {
//        vacancyService.addVacancy(
//                vacancyName,
//                vacancyDescription,
//                category, city,
//                education,
//                experience,
//                workSchedule,
//                employmentType,
//                vacancyAddedDate,
//                salary);
//        return "Vacancy added successfully";
//    }
@PostMapping(value = "/company/addVacancy", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> createVacancy(
        @RequestPart("vacancyDto") @Valid VacancyDto vacancyDto,
        @RequestPart(value = "photoFile", required = false) MultipartFile photoFile) {

    try {
        // Faylı DTO-ya əlavə edirik
        vacancyDto.setPhotoFile(photoFile);

        Vacancy vacancy = vacancyService.addVacancy(vacancyDto);
        return ResponseEntity.ok(vacancy);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("File upload failed: " + e.getMessage());
    }
}

    @GetMapping("auth/vacancies")
    public List<Vacancy> getVacancies() {
        return vacancyService.showAllVacancies();
    }

}
