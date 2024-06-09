package com.mobasir.server.rest.controllers;

import com.mobasir.server.rest.dto.EmployeesDTO;
import com.mobasir.server.rest.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class Employees {

    final EmployeeService employeeService;

    public Employees(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeesDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    public EmployeesDTO getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeesDTO createEmployees(@RequestBody EmployeesDTO employeesDTO) {
        return employeeService.createNewEmployee(employeesDTO);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteEmployeeById(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }
}