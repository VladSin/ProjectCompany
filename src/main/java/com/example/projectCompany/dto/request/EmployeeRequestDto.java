package com.example.projectCompany.dto.request;

import com.example.projectCompany.dto.response.EmployeeResponseDto;
import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import lombok.Data;

@Data
public class EmployeeRequestDto {

    private Long id;

    private String username;

    private String email;

    private double salary;

    private boolean married;

    private String department;

    public static Employee fromRequestEmployee(EmployeeRequestDto request, Department department) {
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setUsername(request.getUsername());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setMarried(request.isMarried());
        employee.setDepartment(department);
        return employee;
    }
}
