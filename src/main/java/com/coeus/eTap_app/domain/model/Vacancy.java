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

    @NotBlank(message = "Vacancy name is required")
    @Size(min = 5, max = 100, message = "Vacancy name must be between 5 and 100 characters.")
    private String vacancyName;

    @NotBlank(message = "Vacancy description is required")
    @Size(min = 50, max = 1500, message = "Vacancy description must be between 50 and 1500 characters.")
    private String vacancyDescription;

    //todo
    @NotBlank(message = "Vacancy name is required")
    private LocalDateTime vacancyAddedDate;
    @NotBlank(message = "Vacancy category is required")
    private Category category;
    @NotBlank(message = "Vacancy city is required")
    private City city;
    @NotBlank(message = "Education level is required")
    private Education education;
    @NotBlank(message = "Type is required")
    private EmploymentType employmentType;
    @NotBlank(message = "Vacancy schedule is required")
    private WorkSchedule workSchedule;
    @NotBlank(message = "Experience is required")
    private Experience experience;
    @NotBlank(message = "Vacancy salary is required")
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
