package com.example.projectCompany.dto.request;

import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import lombok.Data;

import java.sql.Date;

@Data
public class EmployeeRequestDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String birthday;

    private String email;

    private double salary;

    private boolean married;

    private String department;

    public static Employee fromRequestEmployee(EmployeeRequestDto request, Department department) {
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        System.out.println(request.getBirthday());
        employee.setBirthday(Date.valueOf(request.getBirthday()));
        System.out.println(employee.getBirthday());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setMarried(request.isMarried());
        employee.setDepartment(department);
        return employee;
    }
}
