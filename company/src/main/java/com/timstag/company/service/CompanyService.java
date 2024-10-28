package com.timstag.company.service;

import com.timstag.company.dto.CompanyDto;
import com.timstag.company.entity.Company;
import com.timstag.company.exception.ResourceNotFoundException;
import com.timstag.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Create a new company
    public Company createCompany(CompanyDto companyDto) {
        Company company = Company.builder().name(companyDto.getName()).build();
        return companyRepository.save(company);
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Get a company by ID
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
    }

    // Update an existing company
    public Company updateCompany(Long id, CompanyDto companyDto) {
        Company company = getCompanyById(id);
        company.setName(companyDto.getName());
        return companyRepository.save(company);
    }

    // Delete a company by ID
    public void deleteCompany(Long id) {
        Company company = getCompanyById(id);
        companyRepository.delete(company);
    }
}

