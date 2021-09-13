package com.example.projectCompany.controller.api;

import com.example.projectCompany.dto.request.DepartmentRequestDto;
import com.example.projectCompany.dto.response.DepartmentResponseDto;
import com.example.projectCompany.entity.Department;
import com.example.projectCompany.service.CompanyService;
import com.example.projectCompany.service.DepartmentService;
import com.example.projectCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/department/")
public class DepartmentController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(CompanyService companyService, EmployeeService employeeService, DepartmentService departmentService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @PostMapping("save")
    public ResponseEntity<DepartmentResponseDto> saveDepartment(@RequestBody DepartmentRequestDto request) {

        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (departmentService.uniqueName(request.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = new Department();
        department.setName(request.getName());
        department.setWebsite(request.getWebsite());
        department.setLocation(request.getLocation());
        department.setCompany(companyService.getCompanyByName(request.getCompany()));
        departmentService.saveDepartment(department);

        DepartmentResponseDto response = DepartmentResponseDto.fromDepartment(departmentService.getDepartmentByName(request.getName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {
        List<DepartmentResponseDto> response = departmentService.getAllDepartment().stream()
                .map(DepartmentResponseDto::fromDepartment)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/locations/{locations}")
    public ResponseEntity<List<DepartmentResponseDto>> getDepartmentsByLocation(@PathVariable(name = "locations") String locations) {
        List<DepartmentResponseDto> response = departmentService.getAllDepartmentByLocation(locations).stream()
                .map(DepartmentResponseDto::fromDepartment)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable(name = "id") Long id) {
        Department department = departmentService.getDepartmentById(id).orElse(null);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DepartmentResponseDto response = DepartmentResponseDto.fromDepartment(department);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/name/{name}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentByName(@PathVariable(name = "name") String name) {
        Department department = departmentService.getDepartmentByName(name);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DepartmentResponseDto response = DepartmentResponseDto.fromDepartment(department);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "update/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable("id") Long id,
                                                                  @RequestBody DepartmentRequestDto request) {
        if (departmentService.getDepartmentById(id).orElse(null) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        departmentService.updateDepartmentData(DepartmentRequestDto.fromRequestDepartment(request,
                companyService.getCompanyByName(request.getCompany())));
        DepartmentResponseDto response = DepartmentResponseDto.fromDepartment(departmentService.getDepartmentByName(request.getName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }
}
