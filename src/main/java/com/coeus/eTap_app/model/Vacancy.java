package com.coeus.eTap_app.model;

import com.coeus.eTap_app.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class Vacancy {
    private String vacancyName;
    private String vacancyDescription;
    private LocalDateTime vacancyAddedDate;
    private Category category;
    private City city;
    private Education education;
    private EmploymentType employmentType;
    private WorkSchedule workSchedule;
    private Company company;
    private int salary;

    @Enumerated(EnumType.STRING)
    private SalaryRange salaryRange;

    public void setSalary(int salary) {
        this.salary = salary;
        this.salaryRange = SalaryRange.getRangeBySalary(salary);
    }

}
