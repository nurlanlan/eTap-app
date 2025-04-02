package com.coeus.eTap_app.domain.dto;

import com.coeus.eTap_app.enums.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VacancyDto {
    private String vacancyName;
    private String vacancyDescription;
    private LocalDateTime vacancyAddedDate;
    private Category category;
    private City city;
    private Education education;
    private EmploymentType employmentType;
    private WorkSchedule workSchedule;
    private Experience experience;
    private int salary;
    private SalaryRange salaryRange; // 🔥 Salary-dən avtomatik generasiya olunacaq
    private Long companyId; // 🔥 Company obyektinin yerinə ID
}