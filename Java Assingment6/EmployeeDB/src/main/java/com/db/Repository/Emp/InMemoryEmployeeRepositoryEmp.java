package com.db.Repository.Emp;

import com.db.Repository.EmployeeRepository;
import com.db.com.pack.Employee;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryEmployeeRepositoryEmp implements EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    @Override
    public Employee getEmployeeById(int id) {
        return employees.stream().filter(e -> e.getEmpId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employees.add(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        int index = employees.indexOf(getEmployeeById(employee.getEmpId()));
        if (index >= 0) {
            employees.set(index, employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employees.removeIf(e -> e.getEmpId() == id);
    }
}
