package com.example.projectCompany.service.impl;

import com.example.projectCompany.entity.Department;
import com.example.projectCompany.repository.DepartmentRepository;
import com.example.projectCompany.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> getAllDepartmentByLocation(String location) {
        return departmentRepository.findAllByLocation(location);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public void updateDepartmentData(Department department) {
        departmentRepository.updateDepartmentData(
                department.getId(),
                department.getName(),
                department.getWebsite(),
                department.getLocation(),
                department.getCompany(),
                department.getEmployees());
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public boolean uniqueName(String name) {
        return departmentRepository.findByName(name) == null;
    }
}
