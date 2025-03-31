package com.coeus.eTap_app.service;

import com.coeus.eTap_app.model.Company;
import com.coeus.eTap_app.repository.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Company registerCompany(String companyEmail, String companyName, String companyPassword) {
        if (companyRepository.findByCompanyEmail(companyEmail) != null) {
            throw new RuntimeException("Email already in user");
        }
        Company company = new Company();
        company.setCompanyEmail(companyEmail);
        company.setCompanyName(companyName);
        company.setCompanyPassword(passwordEncoder.encode(companyPassword));
        return companyRepository.save(company);
    }

    public Company loginCompany(String companyEmail, String companyPassword) {
        Company company = companyRepository.findByCompanyEmail(companyEmail);
        if (company == null || !passwordEncoder.matches(companyPassword, company.getCompanyPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return company;
    }
}
