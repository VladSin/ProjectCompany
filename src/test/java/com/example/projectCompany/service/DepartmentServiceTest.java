package com.example.projectCompany.service;

import com.example.projectCompany.entity.Company;
import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void saveDepartment() {
        Company company = new Company(null, "Company", "http://Company.com", "Minsk", 1000.50, null);
        companyService.saveCompany(company);

        Department departmentToSave = new Department(null, "SavedCompany", "http://SavedCompany.com", "Minsk", null, companyService.getCompanyByName("Company"), null);
        departmentService.saveDepartment(departmentToSave);
        Department savedDepartment = departmentService.getDepartmentByName("SavedCompany");

        assertNotNull(savedDepartment);
        assertEquals(savedDepartment.getName(), departmentToSave.getName());
        assertEquals(savedDepartment.getLocation(), departmentToSave.getLocation());
        assertEquals(savedDepartment.getWebsite(), departmentToSave.getWebsite());
    }

    @Test
    void getDepartmentById() {
        Department departmentToSave = new Department(null, "Saved1Company", "http://Saved1Company.com", "Minsk", null, null, null);
        departmentService.saveDepartment(departmentToSave);
        Department savedDepartment = departmentService.getDepartmentByName("Saved1Company");
        Department savedDepartmentId = departmentService.getDepartmentById(savedDepartment.getId()).orElse(null);

        assertNotNull(savedDepartmentId);
        assertEquals(savedDepartment.getName(), savedDepartmentId.getName());
        assertEquals(savedDepartment.getLocation(), savedDepartmentId.getLocation());
        assertEquals(savedDepartment.getWebsite(), savedDepartmentId.getWebsite());
    }

    @Test
    void getDepartmentByName() {
        Department departmentToSave = new Department(null, "Saved2Company", "http://Saved2Company.com", "Minsk", null, null, null);
        departmentService.saveDepartment(departmentToSave);
        Department savedDepartment = departmentService.getDepartmentByName("Saved2Company");

        assertNotNull(savedDepartment);
        assertEquals(savedDepartment.getName(), departmentToSave.getName());
        assertEquals(savedDepartment.getLocation(), departmentToSave.getLocation());
        assertEquals(savedDepartment.getWebsite(), departmentToSave.getWebsite());
    }

    @Test
    void getAllDepartmentByLocation() {
        Department departmentToSave = new Department(null, "Saved3Company", "http://Saved3Company.com", "Minsk", null, null, null);
        departmentService.saveDepartment(departmentToSave);
        List<Department> departments = departmentService.getAllDepartmentByLocation("Minsk");
        assertNotNull(departments);
    }

    @Test
    void getAllDepartment() {
        Department departmentToSave = new Department(null, "Saved4Company", "http://Saved4Company.com", "Minsk", null, null, null);
        departmentService.saveDepartment(departmentToSave);
        List<Department> departments = departmentService.getAllDepartment();
        assertNotNull(departments);
    }

    @Test
    void getHeadOfDepartment() {
        Department departmentToSave = new Department(null, "SavedCompanyWithHead", "http://SavedCompanyWithHead.com", "Minsk", null, companyService.getCompanyByName("Company"), null);
        departmentService.saveDepartment(departmentToSave);
        Department savedDepartment = departmentService.getDepartmentByName("SavedCompanyWithHead");

        Employee employee = new Employee(null, "Lion", "Lion", new Date(1980, 10, 10), "lion@gmail.com", 1000.50, true, savedDepartment, null);
        employeeService.saveEmployee(employee);
        Employee savedEmployee = employeeService.getEmployeeByUsername("Lion", "Lion");
        savedDepartment.setHeadId(savedEmployee);
        departmentService.updateDepartmentData(savedDepartment);
        employee = departmentService.getHeadOfDepartment(savedDepartment.getId()).orElse(null);
    }

    @Test
    void updateDepartmentData() {
        Department departmentToSave = new Department(null, "Saved5Company", "http://Saved5Company.com", "Minsk", null, null, null);
        departmentService.saveDepartment(departmentToSave);
        Department savedDepartment = departmentService.getDepartmentByName("Saved5Company");

        Department departmentToUpdate = new Department(savedDepartment.getId(), "Saved6Company", "http://Saved6Company.com", "Brest", null, null, null);
        departmentService.updateDepartmentData(departmentToUpdate);
        Department updatedDepartment = departmentService.getDepartmentByName("Saved6Company");

        assertNotNull(updatedDepartment);
        assertEquals(updatedDepartment.getName(), departmentToUpdate.getName());
        assertEquals(updatedDepartment.getLocation(), departmentToUpdate.getLocation());
        assertEquals(updatedDepartment.getWebsite(), departmentToUpdate.getWebsite());
    }

    @Test
    void deleteDepartment() {
        Department departmentToSave = new Department(null, "NewCompany", "http://NewCompany.com", "Minsk", null, null, null);
        departmentService.saveDepartment(departmentToSave);
        Department savedDepartment = departmentService.getDepartmentByName("NewCompany");
        departmentService.deleteDepartment(savedDepartment.getId());
        assertNull(departmentService.getDepartmentByName(savedDepartment.getName()));
    }

    @Test
    void uniqueName() {
        Department qa = new Department(null, "QACompany", "http://QAcompany.com", "Minsk", null, null, null);
        departmentService.saveDepartment(qa);
        assertFalse(departmentService.uniqueName(qa.getName()));
    }
}