package com.db.Service.Emp;


import com.db.Service.DeptService;
import com.db.com.pack.Dept;
import com.db.com.pack.Employee;
import com.db.Repository.DeptRepository;
import com.db.Repository.EmployeeRepository;
import com.db.Factory.DeptRepositoryFactory;
import com.db.Factory.EmployeeRepositoryFactory;
import java.sql.Connection;
import java.util.List;

public abstract class DeptServiceEmp implements DeptService {

    private static DeptRepository deptRepository;
    private static EmployeeRepository employeeRepository;
    private Connection connection;

    public DeptServiceEmp(int storageInput) {
        deptRepository = DeptRepositoryFactory.getDeptRepositoryInstance(storageInput);
        employeeRepository = EmployeeRepositoryFactory.getEmployeeRepositoryInstance(storageInput);
    }
    public DeptServiceEmp(Connection connection) {
        this.connection = connection;
    }

    public DeptServiceEmp(int storageType, Connection connection) {
    }

    @Override
    public Dept getDeptById(int id) {
        return deptRepository.getDeptById(id);
    }


    @Override
    public String getDeptByEmpId(int empId) {
        Employee employee = employeeRepository.getEmployeeById(empId);
        if (employee != null) {
            return employee.getDept();
        }
        return null;
    }

    @Override
    public List<Dept> getAllDepts() {
        return List.of();
    }
}
