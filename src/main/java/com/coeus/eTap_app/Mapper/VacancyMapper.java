package com.coeus.eTap_app.Mapper;

import com.coeus.eTap_app.domain.dto.VacancyDto;
import com.coeus.eTap_app.domain.model.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "id", ignore = true)
    Vacancy toEntity(VacancyDto vacancyDto);
}