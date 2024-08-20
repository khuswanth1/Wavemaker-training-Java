package Repository;

import com.pack.Dept;

public interface DeptRepository {
    Dept getDeptById(int id);
    boolean addDept(Dept dept);
    boolean updateDept(Dept dept);
    boolean deleteDeptById(int depId);
}
