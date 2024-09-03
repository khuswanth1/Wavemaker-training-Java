package com.wm.Leavemanagement.Service;

import com.wm.Leavemanagement.Employee.Employee;
import com.wm.Leavemanagement.Exception.EmployeeNotFoundException;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import java.util.List;

public interface EmployeeService {
    Employee getByUserName(String userName) throws LoginNotFoundException;
    Employee getById(int userId) throws LoginNotFoundException;
    List<Employee> getEmployeeByUserId(int userId) throws EmployeeNotFoundException;
}