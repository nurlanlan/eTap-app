package com.coeus.eTap_app.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Company {
    private String companyName;
    private String companyEmail;
    private String companyLogo;
    private String companyDescription;
}
