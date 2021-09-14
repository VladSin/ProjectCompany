package com.example.projectCompany;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
class ProjectCompanyApplicationTests {

    void init() {

//        Company itCompany = new Company(null, "ITCompany", "http://company.com", "Minsk", 10000000, null);
//
//        Department qa = new Department(null, "CompanyQA", "http://companyQA.com", "Minsk", itCompany, null);
//        Department ba = new Department(null, "CompanyBA", "http://companyBA.com", "Minsk", itCompany, null);
//        Department hr = new Department(null, "CompanyHR", "http://companyHR.com", "Brest", itCompany, null);
//        Department pm = new Department(null, "CompanyPM", "http://companyPM.com", "Brest", itCompany, null);
//        Department al = new Department(null, "CompanyAL", "http://companyAL.com", "Minsk", itCompany, null);
//
//        Employee employee1 = new Employee(null, "Vasy", "Vasy@gmail.com", 500, true,  qa);
//        Employee employee2 = new Employee(null, "Vany", "Vany@gmail.com", 400, false, qa);
//        Employee employee3 = new Employee(null, "Zina", "Zina@gmail.com", 400, false, qa);
//        Employee employee4 = new Employee(null, "Yana", "Yana@gmail.com", 800, false, hr);
//        Employee employee5 = new Employee(null, "Kate", "Kate@gmail.com", 300, true,  hr);
//        Employee employee6 = new Employee(null, "Gena", "Gena@gmail.com", 700, true,  ba);
//        Employee employee7 = new Employee(null, "Pety", "Pety@gmail.com", 900, true,  pm);
//        Employee employee8 = new Employee(null, "Alex", "Alex@gmail.com", 500, false, al);
//
//        List<Employee> employeesQA = new ArrayList<>();
//        List<Employee> employeesHR = new ArrayList<>();
//        List<Employee> employeesBA = new ArrayList<>();
//        List<Employee> employeesPM = new ArrayList<>();
//        List<Employee> employeesAL = new ArrayList<>();
//
//        employeesAL.add(employee8); al.setEmployees(employeesAL);
//        employeesPM.add(employee7); al.setEmployees(employeesPM);
//        employeesBA.add(employee6); al.setEmployees(employeesBA);
//        employeesHR.add(employee4); employeesHR.add(employee5); al.setEmployees(employeesHR);
//        employeesQA.add(employee1); employeesQA.add(employee2); employeesQA.add(employee3); al.setEmployees(employeesQA);
//
//        List<Department> departments = new ArrayList<>();
//        departments.add(qa);
//        departments.add(al);
//        departments.add(hr);
//        departments.add(ba);
//        departments.add(pm);
//
//        itCompany.setDepartments(departments);
//
//        companyRepository.save(itCompany);
//
//        departmentRepository.save(qa);
//        departmentRepository.save(ba);
//        departmentRepository.save(hr);
//        departmentRepository.save(pm);
//        departmentRepository.save(al);
//
//        employeeRepository.save(employee1);
//        employeeRepository.save(employee2);
//        employeeRepository.save(employee3);
//        employeeRepository.save(employee4);
//        employeeRepository.save(employee5);
//        employeeRepository.save(employee6);
//        employeeRepository.save(employee7);
//        employeeRepository.save(employee8);
    }
}
