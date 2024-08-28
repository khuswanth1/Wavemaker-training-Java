package com.db.Service;

import java.sql.SQLException;
import java.util.*;

import com.db.Exception.DuplicateEmployeeRecordException;
import com.db.com.pack.Employee;

public interface Emp_service {

    Employee getEmployeeById(int empId);
    int addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    boolean updateEmployee(Employee employee);
    boolean deleteEmployeeByEmpId(int empId);
    Employee getEmployeeByEmail(String email);
}