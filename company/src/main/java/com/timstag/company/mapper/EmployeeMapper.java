package com.timstag.company.mapper;

import com.timstag.company.dto.EmployeeDto;
import com.timstag.company.entity.Company;
import com.timstag.company.entity.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeDto dto, Company company) {
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .company(company)
                .build();
    }
    // Convert from Employee entity to EmployeeDTO
    public static EmployeeDto toDTO(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .companyId(employee.getCompany().getId())  // Return the company ID
                .build();
    }
}
