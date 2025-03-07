package com.example.EmployeePayrollApp.controller;
import com.example.EmployeePayrollApp.interfaces.IemployeeService;
import com.example.EmployeePayrollApp.service.EmployeeService;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    IemployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees.");
        List<Employee> employees = employeeService.getAllEmployees();
        log.info("Fetched {} employees.", employees.size());
        return employees;
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            log.info("Found employee with ID: {}", id);
        } else {
            log.warn("Employee with ID: {} not found", id);
        }
        return employee;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("Creating new employee: {}", employee);
        Employee createdEmployee = employeeService.createEmployee(employee);
        log.info("Employee created successfully with ID: {}", createdEmployee.getId());
        return createdEmployee;
    }

    @PutMapping("/{id}")
    public Optional<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        log.info("Updating employee with ID: {}", id);
        Optional<Employee> updatedEmp = employeeService.updateEmployee(id, updatedEmployee);
        if (updatedEmp.isPresent()) {
            log.info("Employee with ID: {} updated successfully.", id);
        } else {
            log.warn("Employee with ID: {} not found for update.", id);
        }
        return updatedEmp;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        log.info("Employee with ID: {} deleted successfully.", id);
    }
}
