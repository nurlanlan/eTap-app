package com.coeus.eTap_app.model;

import com.coeus.eTap_app.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private int salary;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    @Enumerated(EnumType.STRING)
    private SalaryRange salaryRange;

    public void setSalary(int salary) {
        this.salary = salary;
        this.salaryRange = SalaryRange.getRangeBySalary(salary);
    }

}
