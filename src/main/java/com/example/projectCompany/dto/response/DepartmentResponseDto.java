package com.example.projectCompany.dto.response;

import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import lombok.Data;

@Data
public class DepartmentResponseDto {

    private Long id;

    private String name;

    private String website;

    private String location;

    private String head;

    private String company;

    public static DepartmentResponseDto fromDepartment(Department department) {
        DepartmentResponseDto response = new DepartmentResponseDto();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setWebsite(department.getWebsite());
        response.setLocation(department.getLocation());
        if (department.getHeadId() == null){
            response.setHead("null");
        } else {
            response.setHead(department.getHeadId().getFirstName() + " " + department.getHeadId().getLastName());
        }
        if (department.getCompany() == null){
            response.setCompany("null");
        } else {
            response.setCompany(department.getCompany().getName());
        }
        return response;
    }
}
