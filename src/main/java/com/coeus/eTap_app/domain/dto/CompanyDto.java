package com.coeus.eTap_app.domain.dto;

import com.coeus.eTap_app.enums.City;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CompanyDto {

    @NotBlank
    @Email
    String companyEmail;
    @NotBlank
    @Size(min = 8)
    String companyPassword;
    @NotBlank
    String companyName;
    @NotBlank
    City location;
    @NotBlank
    @Email
    String contactEmail;
    @Size(max = 1000)
    String description;
//    @URL
    String logoUrl;

}
