package Service;

import com.pack.Dept;

import java.util.List;

public interface DeptService {
    public Dept getDeptById(int id);
    public boolean addDept(Dept dept);
    public boolean updateDept(Dept dept);
    public boolean deleteDeptById(int depId);
    public String getDeptByEmpId(int empId);

    List<Dept> getAllDepts();
}
