package com.mobasir.server.rest.services;

import com.mobasir.server.rest.dto.EmployeesDTO;
import com.mobasir.server.rest.entities.EmployeeEntity;
import com.mobasir.server.rest.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;
    public Object deleteEmployeeById;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeesDTO getEmployeeById(Long id) {
       EmployeeEntity employeeEntity =  employeeRepository.getReferenceById(id);
       return modelMapper.map(employeeEntity,EmployeesDTO.class);
    }

    public EmployeesDTO createNewEmployee(EmployeesDTO employeesDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeesDTO,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeesDTO.class);
    }

    public List<EmployeesDTO> getAllEmployees() {
        return  employeeRepository.findAll().stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeesDTO.class)).collect(Collectors.toList());
    }

    public boolean deleteEmployeeById(Long id) {
        boolean  isPresent = employeeRepository.existsById(id);
        if (!isPresent) return false;
        employeeRepository.deleteById(id);
        return true;
    }
}
