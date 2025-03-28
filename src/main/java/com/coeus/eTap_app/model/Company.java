package com.coeus.eTap_app.model;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Company {
    private String companyName;
    private String companyEmail;
    private String companyLogo;
    private String companyDescription;
    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;
}
