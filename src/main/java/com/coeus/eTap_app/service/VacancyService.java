package com.coeus.eTap_app.service;

import com.coeus.eTap_app.Mapper.VacancyMapper;
import com.coeus.eTap_app.domain.dto.VacancyDto;
import com.coeus.eTap_app.domain.model.Company;
import com.coeus.eTap_app.enums.*;
import com.coeus.eTap_app.domain.model.Vacancy;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.repository.VacancyRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final SecurityService securityService;
    private final CompanyRepository companyRepository;
    private final VacancyMapper vacancyMapper;


    public VacancyService(VacancyRepository vacancyRepository, SecurityService securityService, CompanyRepository companyRepository, VacancyMapper vacancyMapper) {
        this.vacancyRepository = vacancyRepository;
        this.securityService = securityService;
        this.companyRepository = companyRepository;
        this.vacancyMapper = vacancyMapper;
    }

//    public Vacancy addVacancy(String vacancyName,
//                              String vacancyDescription,
//                              Category category,
//                              City city,
//                              Education education,
//                              Experience experience,
//                              WorkSchedule workSchedule,
//                              EmploymentType employmentType,
//                              LocalDateTime vacancyAddedDate,
//                              int salary) {
//
////        String currentCompanyEmail = securityService.getCurrentUserEmail();
////        Company company = companyRepository.findByCompanyEmail(currentCompanyEmail)
////                .orElseThrow(() -> new RuntimeException("Company not found"));
//
//        Vacancy vacancy = new Vacancy();
//        vacancy.setVacancyName(vacancyName);
//        vacancy.setVacancyDescription(vacancyDescription);
//        vacancy.setCategory(category);
//        vacancy.setCity(city);
//        vacancy.setEducation(education);
//        vacancy.setWorkSchedule(workSchedule);
//        vacancy.setEmploymentType(employmentType);
//        vacancy.setExperience(experience);
//        vacancy.setSalary(salary);
//        vacancy.setVacancyAddedDate(vacancyAddedDate);
////        vacancy.setCompany(company); // Əlaqəni qururuq
//
//        return vacancyRepository.save(vacancy);
//    }
    public Vacancy addVacancy(VacancyDto vacancyDto) {
//        String currentCompanyEmail = securityService.getCurrentUserEmail();
//        Company company = companyRepository.findByCompanyEmail(currentCompanyEmail)
//                .orElseThrow(() -> new RuntimeException("Company not found"));

        Vacancy vacancy = vacancyMapper.toEntity(vacancyDto);
//        vacancy.setCompany(company);
        return vacancyRepository.save(vacancy);
    }


    public List<Vacancy> showAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancies;
    }
}
