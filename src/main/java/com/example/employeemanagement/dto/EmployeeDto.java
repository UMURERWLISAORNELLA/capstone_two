package com.example.employeemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private Long id;

    @NotBlank
    private String name;

    private String position;
    private String department;
    private LocalDate hireDate;
}
