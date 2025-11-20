package org.example.capstone_phase2.Controller;

import lombok.RequiredArgsConstructor;
import org.example.capstone_phase2.Model.Employee;
import org.example.capstone_phase2.repository.EmployeeRepository;
import org.example.capstone_phase2.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee emp, Principal principal) {
        if (principal != null) {
            var user = userRepository.findByUsername(principal.getName()).orElse(null);
            emp.setCreatedBy(user);
        }
        if (emp.getHireDate() == null) emp.setHireDate(LocalDate.now());
        Employee saved = employeeRepository.save(emp);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee emp, Principal principal) {
        return employeeRepository.findById(id).map(existing -> {
            existing.setName(emp.getName());
            existing.setDepartment(emp.getDepartment());
            existing.setPosition(emp.getPosition());
            Employee saved = employeeRepository.save(existing);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return employeeRepository.findById(id).map(e -> {
            employeeRepository.delete(e);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
