package com.timstag.company.service;

import com.timstag.company.dto.EmployeeDto;
import com.timstag.company.entity.Company;
import com.timstag.company.entity.Employee;
import com.timstag.company.exception.ResourceNotFoundException;
import com.timstag.company.mapper.EmployeeMapper;
import com.timstag.company.repository.CompanyRepository;
import com.timstag.company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    // Create a new employee
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Company company = companyRepository.findById(employeeDto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + employeeDto.getCompanyId()));

        Employee employee = EmployeeMapper.toEntity(employeeDto, company);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(savedEmployee);
    }

    // Update an existing employee
    @Transactional
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        // Update employee fields
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        // If the company is changing, update it
        if (!existingEmployee.getCompany().getId().equals(employeeDto.getCompanyId())) {
            Company newCompany = companyRepository.findById(employeeDto.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + employeeDto.getCompanyId()));
            existingEmployee.setCompany(newCompany);
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeMapper.toDTO(updatedEmployee);
    }

    // Get all employees
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));
        return EmployeeMapper.toDTO(employee);
    }

    // Delete an employee by ID
    @Transactional
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));
        employeeRepository.delete(employee);
    }
}
