package Service;

import java.util.*;
import com.pack.Employee;

public interface Emp_service {

    public Employee getEmpId(int id);
    public boolean addEmpId(Employee employee);
    public List<Employee> getAllEmp();
    public boolean updateEmp(Employee employee);
    public boolean deleteEmp(int empId);

}