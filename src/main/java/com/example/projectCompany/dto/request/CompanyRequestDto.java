package com.example.projectCompany.dto.request;

import com.example.projectCompany.entity.Company;
import lombok.Data;

@Data
public class CompanyRequestDto {

    private Long id;

    private String name;

    private String website;

    private String location;

    private double budget;

    public static Company fromRequestCompany(CompanyRequestDto request) {
        Company company = new Company();
        company.setId(request.getId());
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setLocation(request.getLocation());
        company.setBudget(request.getBudget());
        return company;
    }
}
