package com.db.Repository;

import com.db.com.pack.Employee;
import java.util.List;

public interface EmployeeRepository {
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    int addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int id);

    boolean deleteEmployeeByEmpId(int empId);

    Employee getEmployeeByEmail(String email);
}
