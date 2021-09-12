package com.example.projectCompany.dto.response;

import com.example.projectCompany.entity.Company;
import lombok.Data;

@Data
public class CompanyResponseDto {

    private Long id;

    private String name;

    private String website;

    private String location;

    private double budget;

    public static CompanyResponseDto fromCompany(Company company) {
        CompanyResponseDto response = new CompanyResponseDto();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setWebsite(company.getWebsite());
        response.setLocation(company.getLocation());
        response.setBudget(company.getBudget());
        return response;
    }
}
