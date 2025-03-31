package com.coeus.eTap_app.repository;

import com.coeus.eTap_app.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyEmail(String email);
}