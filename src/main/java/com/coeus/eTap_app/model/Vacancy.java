package com.coeus.eTap_app.model;

import com.coeus.eTap_app.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
//@NoArgsConstructor
@Data
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
