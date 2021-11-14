package com.example.projectCompany.service.impl;

import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import com.example.projectCompany.exeption.UsernameNotFoundException;
import com.example.projectCompany.repository.EmployeeRepository;
import com.example.projectCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee getEmployeeByUsername(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllByMarried(boolean married) {
        return employeeRepository.findAllByMarried(married);
    }

    @Override
    public List<Employee> getAllBySalaryAfter(double salary) {
        return employeeRepository.findAllBySalaryAfter(salary);
    }

    @Override
    public List<Employee> getAllBySalaryBefore(double salary) {
        return employeeRepository.findAllBySalaryBefore(salary);
    }

    @Override
    public List<Employee> getAllBySalaryBetween(double minSalary, double maxSalary) {
        return employeeRepository.findAllBySalaryBetween(minSalary, maxSalary);
    }

    @Override
    public List<Employee> getAllByDepartment(Department department) {
        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    public void updateEmployeeData(Employee employee) {
        employeeRepository.updateEmployeeData(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthday(),
                employee.getEmail(),
                employee.getSalary(),
                employee.isMarried(),
                employee.getDepartment());
    }

    @Override
    public boolean checkEmail(String email) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
        return true;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
