package Repository.Emp;

import java.util.List;

import com.pack.Employee;
import Repository.EmployeeRepository;

public class InFileEmployeeRepositoryEmp implements EmployeeRepository{

    @Override
    public boolean addEmpId(Employee employee) {

        return false;
    }

    @Override
    public boolean deleteEmp(int empId) {
        return false;
    }

    @Override
    public List<Employee> getAllEmp() {
        return null;
    }

    @Override
    public Employee getEmpId(int id) {
        return null;
    }

    @Override
    public boolean updateEmp(Employee employee) {
        return false;
    }

}