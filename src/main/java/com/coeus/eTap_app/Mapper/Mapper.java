package com.coeus.eTap_app.Mapper;

import com.coeus.eTap_app.domain.dto.VacancyDto;
import com.coeus.eTap_app.domain.model.Company;
import com.coeus.eTap_app.domain.model.Vacancy;
import com.coeus.eTap_app.enums.SalaryRange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Vacancy toEntity(VacancyDto dto, Company company) {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyName(dto.getVacancyName());
        vacancy.setVacancyDescription(dto.getVacancyDescription());
        vacancy.setVacancyAddedDate(dto.getVacancyAddedDate());
        vacancy.setCategory(dto.getCategory());
        vacancy.setCity(dto.getCity());
        vacancy.setEducation(dto.getEducation());
        vacancy.setEmploymentType(dto.getEmploymentType());
        vacancy.setWorkSchedule(dto.getWorkSchedule());
        vacancy.setExperience(dto.getExperience());
        vacancy.setSalary(dto.getSalary()); // Salary təyin olunur
        vacancy.setCompany(company); // Şirkət təyin olunur

        // 🔥 SalaryRange avtomatik set olunsun
        vacancy.setSalaryRange(SalaryRange.getRangeBySalary(dto.getSalary()));

        return vacancy;
    }



}
