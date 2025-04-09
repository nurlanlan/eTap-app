package com.coeus.eTap_app.domain.model;

import com.coeus.eTap_app.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;


@RequiredArgsConstructor
//@NoArgsConstructor
@Data
@Entity
@Validated
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
