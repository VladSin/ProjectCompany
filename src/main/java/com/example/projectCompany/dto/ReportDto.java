package com.example.projectCompany.dto;

import com.example.projectCompany.entity.Company;
import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private String companyName;
    private String departmentNumberInCompany;
    private String employeeNumberInCompany;
    private String departmentName;
    private String employeeNumberInDepartment;
    private String employeeInformation;

    public static List<ReportDto> toReportDto(List<Employee> employees, List<Department> departments, List<Company> companies) throws NullPointerException{
        List<ReportDto> response = new ArrayList<>();

        try {
            for (Company c : companies) {
                ReportDto companyReport = new ReportDto();
                companyReport.setCompanyName(c.getName());
                companyReport.setDepartmentNumberInCompany(String.valueOf(getNumberOfDepartmentsInCompany(c, departments)));
                companyReport.setEmployeeNumberInCompany(String.valueOf(getNumberOfEmployeesInCompany(c, departments, employees)));
                companyReport.setDepartmentName("");
                companyReport.setEmployeeNumberInDepartment("");
                companyReport.setEmployeeInformation("");
                response.add(companyReport);

                response.addAll(getReportByCompany(c, departments, employees));
            }
            return response;
        } catch (NullPointerException e){
            return response;
        }
    }

    private static List<ReportDto> getReportByCompany(Company company, List<Department> departments, List<Employee> employees) throws NullPointerException{
        List<ReportDto> departmentReportList = new ArrayList<>();

        try {
            for (Department d : departments) {
                if (d.getCompany().getId().equals(company.getId())) {
                    ReportDto departmentReport = new ReportDto();
                    departmentReport.setCompanyName("");
                    departmentReport.setDepartmentNumberInCompany("");
                    departmentReport.setEmployeeNumberInCompany("");
                    departmentReport.setDepartmentName(d.getName());
                    departmentReport.setEmployeeNumberInDepartment(String.valueOf(getNumberOfEmployeesInDepartment(d, company, employees)));
                    departmentReport.setEmployeeInformation("");
                    departmentReportList.add(departmentReport);

                    departmentReportList.addAll(getReportByDepartment(d, employees));
                }
            }
            return departmentReportList;
        } catch (NullPointerException e){
            return departmentReportList;
        }
    }


    private static List<ReportDto> getReportByDepartment(Department department, List<Employee> employees) throws NullPointerException{
        List<ReportDto> employeeReportList = new ArrayList<>();

        try {
            for (Employee e : employees) {
                if (e.getDepartment().getId().equals(department.getId())) {
                    ReportDto employeeReport = new ReportDto();
                    employeeReport.setCompanyName("");
                    employeeReport.setDepartmentNumberInCompany("");
                    employeeReport.setEmployeeNumberInCompany("");
                    employeeReport.setDepartmentName("");
                    employeeReport.setEmployeeNumberInDepartment("");
                    employeeReport.setEmployeeInformation(e.getFirstName() + " " + e.getLastName() + ", " + e.getEmail());
                    employeeReportList.add(employeeReport);
                }
            }
            return employeeReportList;
        } catch (NullPointerException e){
            return employeeReportList;
        }
    }

    private static int getNumberOfDepartmentsInCompany(Company company, List<Department> departments) {
        int departmentNumberInCompany = 0;
        try {
            for (Department d : departments) {
                if (d.getCompany().getId().equals(company.getId())) {
                    departmentNumberInCompany++;
                }
            }
            return departmentNumberInCompany;
        } catch (NullPointerException e){
            return departmentNumberInCompany;
        }
    }

    private static int getNumberOfEmployeesInCompany(Company company, List<Department> departments, List<Employee> employees) {
        int employeeNumberInCompany = 0;
        try {
            for (Department d : departments) {
                if (d.getCompany().getId().equals(company.getId())) {
                    employeeNumberInCompany += getNumberOfEmployeesInDepartment(d, company, employees);
                }
            }
            return employeeNumberInCompany;
        } catch (NullPointerException e){
            return employeeNumberInCompany;
        }
    }

    private static int getNumberOfEmployeesInDepartment(Department department, Company company, List<Employee> employees) {
        int employeeNumberInDepartment = 0;
        try {
            for (Employee e : employees) {
                if (department.getCompany().getId().equals(company.getId()) && e.getDepartment().getId().equals(department.getId())) {
                    employeeNumberInDepartment++;
                }
            }
            return employeeNumberInDepartment;
        } catch (NullPointerException e){
            return employeeNumberInDepartment;
        }
    }

    public static Map<String, String> getReportResult(List<Employee> employees, List<Department> departments, List<Company> companies) throws NullPointerException{
        Map<String, String> response = new HashMap<>();

        int employeeNumber = 0;
        int departmentNumber = 0;
        int companyNumber = 0;

        for (Employee e : employees) {
            employeeNumber++;
        }
        for (Department d : departments) {
            departmentNumber++;
        }
        for (Company c : companies) {
            companyNumber++;
        }

        response.put("Total companies", String.valueOf(companyNumber));
        response.put("Total departments", String.valueOf(departmentNumber));
        response.put("Total employees", String.valueOf(employeeNumber));
        return response;
    }
}
