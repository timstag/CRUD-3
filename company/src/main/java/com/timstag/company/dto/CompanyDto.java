package com.timstag.company.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class CompanyDto {
    private Long id;
    private String name;
    private List<Long> employeeIds;
}
