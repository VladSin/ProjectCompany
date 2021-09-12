package com.example.projectCompany.dto.request;

import com.example.projectCompany.entity.Company;
import com.example.projectCompany.entity.Department;
import lombok.Data;

@Data
public class DepartmentRequestDto {

    private Long id;

    private String name;

    private String website;

    private String location;

    private String company;

    public static Department fromRequestDepartment(DepartmentRequestDto request, Company company) {
        Department department = new Department();
        department.setId(request.getId());
        department.setName(request.getName());
        department.setWebsite(request.getWebsite());
        department.setLocation(request.getLocation());
        department.setCompany(company);
        return department;
    }
}
