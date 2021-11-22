package com.example.projectCompany;

import com.example.projectCompany.entity.Company;
import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import com.example.projectCompany.service.CompanyService;
import com.example.projectCompany.service.DepartmentService;
import com.example.projectCompany.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@SpringBootTest
@Transactional
@Commit
class ProjectCompanyApplicationTests {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @Test
    void init() {

        Company itCompany = new Company(null, "ITCompany", "http://company.com", "Minsk", 10000000, null);

        Department qa = new Department(null, "CompanyQA", "http://companyQA.com", "Minsk", null, itCompany, null);
        Department ba = new Department(null, "CompanyBA", "http://companyBA.com", "Minsk", null, itCompany, null);
        Department hr = new Department(null, "CompanyHR", "http://companyHR.com", "Brest", null, itCompany, null);
        Department pm = new Department(null, "CompanyPM", "http://companyPM.com", "Brest", null, itCompany, null);
        Department al = new Department(null, "CompanyAL", "http://companyAL.com", "Minsk", null, itCompany, null);

        Employee employee1 = new Employee(null, "Vasy", "Vasy", new Date(1980, 10, 10), "Vasy@gmail.com", 500, true,  qa, qa);
        Employee employee2 = new Employee(null, "Vany", "Vany", new Date(1980, 10, 10), "Vany@gmail.com", 400, false, qa, null);
        Employee employee3 = new Employee(null, "Zina", "Zina", new Date(1980, 10, 10), "Zina@gmail.com", 400, false, qa, null);
        Employee employee4 = new Employee(null, "Yana", "Yana", new Date(1980, 10, 10), "Yana@gmail.com", 800, false, hr, hr);
        Employee employee5 = new Employee(null, "Kate", "Kate", new Date(1980, 10, 10), "Kate@gmail.com", 300, true,  hr, null);
        Employee employee6 = new Employee(null, "Gena", "Gena", new Date(1980, 10, 10), "Gena@gmail.com", 700, true,  ba, ba);
        Employee employee7 = new Employee(null, "Pety", "Pety", new Date(1980, 10, 10), "Pety@gmail.com", 900, true,  pm, pm);
        Employee employee8 = new Employee(null, "Alex", "Alex", new Date(1980, 10, 10), "Alex@gmail.com", 500, false, al, al);

        companyService.saveCompany(itCompany);

        departmentService.saveDepartment(qa);
        departmentService.saveDepartment(ba);
        departmentService.saveDepartment(hr);
        departmentService.saveDepartment(pm);
        departmentService.saveDepartment(al);

        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        employeeService.saveEmployee(employee3);
        employeeService.saveEmployee(employee4);
        employeeService.saveEmployee(employee5);
        employeeService.saveEmployee(employee6);
        employeeService.saveEmployee(employee7);
        employeeService.saveEmployee(employee8);
    }

    @Test
    void checkRegx() {
        Employee employeeGG1 = new Employee(null, "&^%@", "Vasy", new Date(1980, 10, 10), "Vasy@gmail.com", 500, true,  null, null);
        Employee employeeGG2 = new Employee(null, "Vasy", "^&$#&@", new Date(1980, 10, 10), "Vasy@gmail.com", 500, true,  null, null);
        Employee employeeGG3 = new Employee(null, "Vasy", "Vasy", new Date(1980, 10, 10), "Vasygmailcom", 500, true,  null, null);

        employeeService.saveEmployee(employeeGG1);
        employeeService.saveEmployee(employeeGG2);
        employeeService.saveEmployee(employeeGG3);
    }

}
