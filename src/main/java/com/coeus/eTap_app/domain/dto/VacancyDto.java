package com.coeus.eTap_app.domain.dto;

import com.coeus.eTap_app.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VacancyDto {

    @NotBlank(message = "Vacancy name is required")
    @Size(min = 5, max = 100, message = "Vacancy name must be between 5 and 100 characters.")
    private String vacancyName;

    @NotBlank(message = "Vacancy description is required")
    @Size(min = 50, max = 1500, message = "Vacancy description must be between 50 and 1500 characters.")
    private String vacancyDescription;

    @NotNull(message = "Vacancy added date is required")
    @Schema(example = "2025-04-02T14:30:00")
    private LocalDateTime vacancyAddedDate;

    @NotNull(message = "Vacancy category is required")
    private Category category;

    @NotNull(message = "Vacancy city is required")
    private City city;

    @NotNull(message = "Education level is required")
    private Education education;

    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;

    @NotNull(message = "Vacancy schedule is required")
    private WorkSchedule workSchedule;

    @NotNull(message = "Experience is required")
    private Experience experience;

    @Min(value = 300, message = "Salary must be at least 300")
    @Max(value = 100000, message = "Salary cannot be more than 100000")
    private int salary;
}
