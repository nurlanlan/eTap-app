package com.coeus.eTap_app.service;

import com.coeus.eTap_app.Mapper.CompanyMapper;
import com.coeus.eTap_app.domain.dto.CompanyDto;
import com.coeus.eTap_app.domain.model.Company;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Company registerCompany(CompanyDto companyDto) {
        if (companyRepository.findByCompanyEmail(companyDto.getCompanyEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
//        Company company = new Company();
//        company.setCompanyEmail(companyEmail);
//        company.setCompanyPassword(passwordEncoder.encode(companyPassword));
        Company company = new Company();
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyPassword(passwordEncoder.encode(companyDto.getCompanyPassword()));
        return companyRepository.save(company);
    }

    public Company updateCompany(CompanyDto companyDto) {
        Company company = companyRepository.findByCompanyEmail(companyDto.getCompanyEmail()).get();
        Company updatedCompany = companyMapper.toEntity(companyDto);
        return companyRepository.save(updatedCompany);
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
