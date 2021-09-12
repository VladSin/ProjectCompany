package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.EmployeeRequestDto;
import com.example.projectCompany.dto.response.EmployeeResponseDto;
import com.example.projectCompany.entity.Employee;
import com.example.projectCompany.exeption.UsernameNotFoundException;
import com.example.projectCompany.service.DepartmentService;
import com.example.projectCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee/")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @PostMapping("save")
    public ResponseEntity<EmployeeResponseDto> saveEmployee(@RequestBody EmployeeRequestDto request) throws UsernameNotFoundException {

        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (employeeService.checkEmail(request.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = new Employee();
        if (request.getDepartment() != null) {
            employee.setDepartment(departmentService.getDepartmentByName(request.getDepartment()));
        }
        employee.setUsername(request.getUsername());
        employee.setEmail(request.getEmail());
        employee.setMarried(request.isMarried());
        employee.setSalary(request.getSalary());
        employeeService.saveEmployee(employee);

        EmployeeResponseDto response = EmployeeResponseDto.fromEmployee(employeeService.getEmployeeByEmail(request.getEmail()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
        List<EmployeeResponseDto> response = employeeService.getAll().stream()
                .map(EmployeeResponseDto::fromEmployee)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getEmployeeById(id).orElse(null);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        EmployeeResponseDto response = EmployeeResponseDto.fromEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/username/{username}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByUsername(@PathVariable(name = "username") String username) {
        Employee employee = employeeService.getEmployeeByUsername(username);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        EmployeeResponseDto response = EmployeeResponseDto.fromEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/email/{email}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByEmail(@PathVariable(name = "email") String email) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        EmployeeResponseDto response = EmployeeResponseDto.fromEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "update/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable("id") Long id,
                                                                  @RequestBody EmployeeRequestDto request) {
        if (employeeService.getEmployeeById(id).orElse(null) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        employeeService.updateEmployeeData(EmployeeRequestDto.fromRequestEmployee(request,
                departmentService.getDepartmentByName(request.getDepartment())));

        EmployeeResponseDto response = EmployeeResponseDto.fromEmployee(employeeService.getEmployeeByEmail(
                request.getEmail()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
