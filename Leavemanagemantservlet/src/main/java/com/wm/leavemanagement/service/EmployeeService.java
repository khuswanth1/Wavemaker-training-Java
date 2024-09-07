package com.wm.leavemanagement.service;

import com.wm.leavemanagement.pojo.Employee;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;

import java.util.List;

public interface EmployeeService {

    Employee getByUserName(String userName) throws loginNotFoundException;

    Employee getById(int userId) throws loginNotFoundException;

    List<Employee> getEmployeeByUserId(int userId) throws employeeNotFoundException;
}