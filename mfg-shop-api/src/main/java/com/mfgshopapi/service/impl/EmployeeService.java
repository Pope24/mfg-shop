package com.mfgshopapi.service.impl;

import com.mfgshopapi.model.Employee;
import com.mfgshopapi.repository.IEmployeeRepository;
import com.mfgshopapi.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).get()
                ;
    }
}
