package com.example.projectCompany.dto.response;

import com.example.projectCompany.entity.Employee;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

@Data
public class EmployeeResponseDto {

    private Long id;

    private String username;

    private String age;

    private String birthday;

    private String email;

    private double salary;

    private boolean married;

    private String department;

    public static EmployeeResponseDto fromEmployee(Employee employee) {


        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setId(employee.getId());
        response.setUsername(employee.getFirstName() + " " + employee.getLastName());
        response.setAge(String.valueOf(calculateAge(employee.getBirthday(), new Date(Calendar.getInstance().getTime().getTime()))));
        response.setBirthday(String.valueOf(employee.getBirthday()));
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());
        response.setMarried(employee.isMarried());
        if (employee.getDepartment() == null){
            response.setDepartment("null");
        } else {
            response.setDepartment(employee.getDepartment().getName());
        }

        return response;
    }

    public static int calculateAge(Date birthDate, Date currentDate) {
        long timeBetween = currentDate.getTime() - birthDate.getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        return  (int) Math.floor(yearsBetween);
    }
}
