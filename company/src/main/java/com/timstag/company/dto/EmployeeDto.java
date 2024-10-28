package com.timstag.company.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long companyId;
}
