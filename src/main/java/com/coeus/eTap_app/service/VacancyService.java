package com.coeus.eTap_app.service;

import com.coeus.eTap_app.enums.*;
import com.coeus.eTap_app.model.Company;
import com.coeus.eTap_app.model.Vacancy;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.repository.VacancyRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final SecurityService securityService;
    private final CompanyRepository companyRepository;

    public VacancyService(VacancyRepository vacancyRepository, SecurityService securityService, CompanyRepository companyRepository) {
        this.vacancyRepository = vacancyRepository;
        this.securityService = securityService;
        this.companyRepository = companyRepository;
    }

    public Vacancy addVacancy(String vacancyName,
                              String vacancyDescription,
                              Category category,
                              City city,
                              Education education,
                              Experience experience,
                              WorkSchedule workSchedule,
                              EmploymentType employmentType,
                              int salary) {

        // Hazırki login olmuş company-ni tapırıq
        String currentCompanyEmail = securityService.getCurrentUserEmail();
        Company company = companyRepository.findByCompanyEmail(currentCompanyEmail)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyName(vacancyName);
        vacancy.setVacancyDescription(vacancyDescription);
        vacancy.setCategory(category);
        vacancy.setCity(city);
        vacancy.setEducation(education);
        vacancy.setWorkSchedule(workSchedule);
        vacancy.setEmploymentType(employmentType);
        vacancy.setExperience(experience);
        vacancy.setSalary(salary);
        vacancy.setCompany(company); // Əlaqəni qururuq

        return vacancyRepository.save(vacancy);
    }

    public List<Vacancy> showAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancies;
    }
}
