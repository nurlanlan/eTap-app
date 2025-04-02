package com.coeus.eTap_app.repository;

import com.coeus.eTap_app.domain.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
     Vacancy findVacancyById(long id);
}
