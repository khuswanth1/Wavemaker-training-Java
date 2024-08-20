package Service.Emp;


import Service.DeptService;
import com.pack.Dept;
import com.pack.Employee;
import Repository.DeptRepository;
import Repository.EmployeeRepository;
import Factory.DeptRepositoryFactory;
import Factory.EmployeeRepositoryFactory;

import java.util.List;

public  class DeptServiceEmp implements DeptService {

    private static DeptRepository deptRepository;
    private static EmployeeRepository employeeRepository;

    public DeptServiceEmp(int storageInput) {
        deptRepository = DeptRepositoryFactory.getDeptRepositoryInstance(storageInput);
        employeeRepository = EmployeeRepositoryFactory.getEmployeeRepositoryInstance(storageInput);
    }

    @Override
    public Dept getDeptById(int id) {
        return deptRepository.getDeptById(id);
    }

    @Override
    public boolean addDept(Dept dept) {
        return deptRepository.addDept(dept);
    }

    @Override
    public boolean updateDept(Dept dept) {
        return deptRepository.updateDept(dept);
    }

    @Override
    public boolean deleteDeptById(int depId) {
        return deptRepository.deleteDeptById(depId);
    }

    @Override
    public String getDeptByEmpId(int empId) {
        Employee employee = employeeRepository.getEmpId(empId);
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
