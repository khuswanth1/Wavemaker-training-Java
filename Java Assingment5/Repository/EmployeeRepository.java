package Repository;

import com.pack.Employee;
import java.util.*;

public interface EmployeeRepository {

    public Employee getEmpId(int id);
    public boolean addEmpId(Employee employee);
    public List<Employee> getAllEmp();
    public boolean updateEmp(Employee employee);
    public boolean deleteEmp(int empId );
}