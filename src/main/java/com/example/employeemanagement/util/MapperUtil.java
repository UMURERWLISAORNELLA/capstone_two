package com.example.employeemanagement.util;

import com.example.employeemanagement.dto.EmployeeDto;
import com.example.employeemanagement.model.Employee;

public class MapperUtil {

    public static Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .position(dto.getPosition())
                .department(dto.getDepartment())
                .hireDate(dto.getHireDate())
                .build();
    }

    public static EmployeeDto toDto(Employee e) {
        if (e == null) return null;
        EmployeeDto dto = new EmployeeDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setPosition(e.getPosition());
        dto.setDepartment(e.getDepartment());
        dto.setHireDate(e.getHireDate());
        return dto;
    }
}
