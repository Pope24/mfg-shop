package com.mfgshopapi.repository;

import com.mfgshopapi.model.Employee;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Boolean existsEmployeeByEmail(String email);
    Employee findEmployeeByEmail(String email);
    Employee findEmployeeByAccount_NameAccount(String nameAccount);
}
