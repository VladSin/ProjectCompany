package com.example.projectCompany.service;

import com.example.projectCompany.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {

    Company saveCompany(Company company);

    Optional<Company> getCompanyById(Long id);

    Company getCompanyByName(String name);

    List<Company> getAllCompanyByLocation(String location);

    List<Company> getAllCompany();

    void updateCompanyData(Company company);

    void deleteCompany(Long id);

    boolean uniqueName(String name);
}
