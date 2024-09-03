package com.wm.Leavemanagement.Repository;

import com.wm.Leavemanagement.Employee.Employee;
import com.wm.Leavemanagement.Exception.EmployeeNotFoundException;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import java.util.List;

public interface EmployeeRepository {
    Employee getByUserName(String userName) throws LoginNotFoundException;
    Employee getById(int userId) throws LoginNotFoundException;
    List<Employee> getEmployeesByManagerId(int userId) throws EmployeeNotFoundException;
}