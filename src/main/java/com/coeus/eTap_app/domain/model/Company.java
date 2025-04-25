package com.coeus.eTap_app.domain.model;

import com.coeus.eTap_app.enums.City;
import jakarta.persistence.*;
import lombok.Data;
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

    @Column(unique = true, nullable = false)
    private String companyEmail;
    //todo
    //email for users to send CV
    private String contactEmail;
    private String companyName;
    private String companyPassword;
    private String companyLogo;
    private City city;
    private String companyDescription;
    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;
}
