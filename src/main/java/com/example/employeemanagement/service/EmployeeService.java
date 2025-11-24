package com.example.employeemanagement.service;

import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee create(Employee e) { return repo.save(e); }
    public List<Employee> findAll() { return repo.findAll(); }
    public Employee findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }
    public Employee update(Long id, Employee dto) {
        Employee existing = findById(id);
        existing.setName(dto.getName());
        existing.setPosition(dto.getPosition());
        existing.setDepartment(dto.getDepartment());
        existing.setHireDate(dto.getHireDate());
        return repo.save(existing);
    }
    public void delete(Long id) { repo.delete(findById(id)); }
}
