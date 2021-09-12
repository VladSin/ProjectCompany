package com.example.projectCompany.service;


import com.example.projectCompany.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentService {

    Department saveDepartment(Department department);

    Optional<Department> getDepartmentById(Long id);

    Department getDepartmentByName(String name);

    List<Department> getAllDepartmentByLocation(String location);

    List<Department> getAllDepartment();

    void updateDepartmentData(Department department);

    void deleteDepartment(Long id);

    boolean uniqueName(String name);
}
