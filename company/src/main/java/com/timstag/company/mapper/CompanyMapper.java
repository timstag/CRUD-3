package com.timstag.company.mapper;

import com.timstag.company.dto.CompanyDto;
import com.timstag.company.entity.Company;
import com.timstag.company.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    // Convert from Company entity to CompanyDTO
    public CompanyDto toDTO(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .employeeIds(
                        company.getEmployees() != null
                                ? company.getEmployees().stream().map(Employee::getId).collect(Collectors.toList())
                                : null
                )
                .build();
    }

    // Convert from CompanyDTO to Company entity
    public Company toEntity(CompanyDto companyDTO) {
        return Company.builder()
                .name(companyDTO.getName())
                .build();
    }

    // Convert a list of Company entities to a list of CompanyDTOs
    public List<CompanyDto> toDTOList(List<Company> companies) {
        return companies.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

