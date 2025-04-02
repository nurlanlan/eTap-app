package com.coeus.eTap_app.service;

import com.coeus.eTap_app.domain.model.Company;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Company registerCompany(String companyEmail,  String companyPassword,String companyName) {
        if (companyRepository.findByCompanyEmail(companyEmail).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        Company company = new Company();
        company.setCompanyEmail(companyEmail);
        company.setCompanyName(companyName);
        company.setCompanyPassword(passwordEncoder.encode(companyPassword));
        return companyRepository.save(company);
    }

    public String loginCompany(String companyEmail, String companyPassword) {
        Company company = companyRepository.findByCompanyEmail(companyEmail)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(companyPassword, company.getCompanyPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(companyEmail, "COMPANY");
    }
}
