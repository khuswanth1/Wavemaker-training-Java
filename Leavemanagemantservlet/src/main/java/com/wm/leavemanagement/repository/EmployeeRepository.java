package com.wm.leavemanagement.repository;

import com.wm.leavemanagement.pojo.Employee;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;

import java.util.List;

public interface EmployeeRepository {
    Employee getByUserName(String userName) throws loginNotFoundException;

    Employee getById(int userId) throws loginNotFoundException;

    List<Employee> getEmployeesByManagerId(int userId) throws employeeNotFoundException;
}