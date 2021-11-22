package com.example.projectCompany.service;


import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentService {

    void saveDepartment(Department department);

    Optional<Department> getDepartmentById(Long id);

    Department getDepartmentByName(String name);

    List<Department> getAllDepartmentByLocation(String location);

    List<Department> getAllDepartment();

    Optional<Employee> getHeadOfDepartment(Long id);

    void updateDepartmentData(Department department);

    void deleteDepartment(Long id);

    boolean uniqueName(String name);
}
