package com.coeus.eTap_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "Companies_eTap")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String companyEmail;
    private String companyPassword;
    private String companyLogo;
    private String companyDescription;
    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;
}
