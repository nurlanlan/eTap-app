package com.coeus.eTap_app.Mapper;

import com.coeus.eTap_app.domain.dto.VacancyDto;
import com.coeus.eTap_app.domain.model.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "photoUrl", ignore = true) // S3 URL-i manual olaraq set edəcəyik
    Vacancy toEntity(VacancyDto vacancyDto);

//    @Mapping(target = "photoFile", ignore = true) // MultipartFile DTO-da olacaq, entity-də yox
    VacancyDto toDto(Vacancy vacancy);
}