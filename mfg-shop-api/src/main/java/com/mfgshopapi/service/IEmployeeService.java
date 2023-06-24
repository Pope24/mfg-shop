package com.mfgshopapi.service;

import com.mfgshopapi.model.Employee;

public interface IEmployeeService {
    Employee findById(int id);
}
