package org.example.capstone_phase2.repository;

import org.example.capstone_phase2.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
