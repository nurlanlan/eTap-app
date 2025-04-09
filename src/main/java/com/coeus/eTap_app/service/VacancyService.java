package com.coeus.eTap_app.service;

import com.coeus.eTap_app.Mapper.VacancyMapper;
import com.coeus.eTap_app.domain.dto.VacancyDto;
import com.coeus.eTap_app.domain.model.Company;
import com.coeus.eTap_app.enums.*;
import com.coeus.eTap_app.domain.model.Vacancy;
import com.coeus.eTap_app.repository.CompanyRepository;
import com.coeus.eTap_app.repository.VacancyRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final SecurityService securityService;
    private final CompanyRepository companyRepository;
    private final VacancyMapper vacancyMapper;
    private final S3Service s3Service;


    public VacancyService(VacancyRepository vacancyRepository, SecurityService securityService, CompanyRepository companyRepository, VacancyMapper vacancyMapper, S3Service s3Service) {
        this.vacancyRepository = vacancyRepository;
        this.securityService = securityService;
        this.companyRepository = companyRepository;
        this.vacancyMapper = vacancyMapper;
        this.s3Service = s3Service;
    }


    public Vacancy addVacancy(VacancyDto vacancyDto) {
//        String currentCompanyEmail = securityService.getCurrentUserEmail();
//        Company company = companyRepository.findByCompanyEmail(currentCompanyEmail)
//                .orElseThrow(() -> new RuntimeException("Company not found"));

        Vacancy vacancy = vacancyMapper.toEntity(vacancyDto);
//        vacancy.setCompany(company);
        // Foto yükləmə
        if (vacancyDto.getPhotoFile() != null && !vacancyDto.getPhotoFile().isEmpty()) {
            String photoUrl = null;
            try {
                photoUrl = s3Service.uploadFile(vacancyDto.getPhotoFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vacancy.setVacancyImage(photoUrl);
        }
        return vacancyRepository.save(vacancy);
    }


    public List<Vacancy> showAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancies;
    }
}
