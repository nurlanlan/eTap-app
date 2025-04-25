package com.coeus.eTap_app.Mapper;

import com.coeus.eTap_app.domain.dto.CompanyDto;
import com.coeus.eTap_app.domain.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
//    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactEmail", ignore = true)
    @Mapping(target = "companyPassword", ignore = true)
    Company toEntity(CompanyDto companyDto);


//    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactEmail", ignore = true)
    @Mapping(target = "companyPassword", ignore = true)
    CompanyDto toDto(Company company);
}
