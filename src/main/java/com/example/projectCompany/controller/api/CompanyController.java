package com.example.projectCompany.controller.api;

import com.example.projectCompany.dto.request.CompanyRequestDto;
import com.example.projectCompany.dto.response.CompanyResponseDto;
import com.example.projectCompany.entity.Company;
import com.example.projectCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/company/")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("save")
    public ResponseEntity<CompanyResponseDto> saveCompany(@RequestBody CompanyRequestDto request) {

        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!companyService.uniqueName(request.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Company company = new Company();
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setLocation(request.getLocation());
        company.setBudget(request.getBudget());
        companyService.saveCompany(company);

        CompanyResponseDto response = CompanyResponseDto.fromCompany(companyService.getCompanyByName(request.getName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<CompanyResponseDto>> getCompanies() {
        List<CompanyResponseDto> response = companyService.getAllCompany().stream()
                .map(CompanyResponseDto::fromCompany)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/location/{location}")
    public ResponseEntity<List<CompanyResponseDto>> getCompaniesByLocation(@PathVariable(name = "location") String location) {
        List<CompanyResponseDto> response = companyService.getAllCompanyByLocation(location).stream()
                .map(CompanyResponseDto::fromCompany)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<CompanyResponseDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        Company company = companyService.getCompanyById(id).orElse(null);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CompanyResponseDto response = CompanyResponseDto.fromCompany(company);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/name/{name}")
    public ResponseEntity<CompanyResponseDto> getCompanyByName(@PathVariable(name = "name") String name) {
        Company company = companyService.getCompanyByName(name);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CompanyResponseDto response = CompanyResponseDto.fromCompany(company);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "update/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable("id") Long id,
                                                            @RequestBody CompanyRequestDto request) {
        if (companyService.getCompanyById(id).orElse(null) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        companyService.updateCompanyData(CompanyRequestDto.fromRequestCompany(request));
        CompanyResponseDto response = CompanyResponseDto.fromCompany(companyService.getCompanyByName(request.getName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company DELETED", HttpStatus.OK);
    }
}
