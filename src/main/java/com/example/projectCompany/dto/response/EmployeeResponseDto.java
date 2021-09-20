package com.example.projectCompany.dto.response;

import com.example.projectCompany.entity.Employee;
import lombok.Data;

@Data
public class EmployeeResponseDto {

    private Long id;

    private String username;

    private String email;

    private double salary;

    private boolean married;

    private String department;

    public static EmployeeResponseDto fromEmployee(Employee employee) {
        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setId(employee.getId());
        response.setUsername(employee.getUsername());
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());
        response.setMarried(employee.isMarried());
        if (response.getDepartment() == null){
            response.setDepartment("null");
        } else {
            response.setDepartment(employee.getDepartment().getName());
        }

        return response;
    }
}
