package Repository.Emp;

import Repository.DeptRepository;
import com.pack.Dept;

public class InFileDeptRepositoryEmp implements DeptRepository {

    @Override
    public Dept getDeptById(int id) {
        return null;
    }

    @Override
    public boolean addDept(Dept dept) {
        return false;
    }

    @Override
    public boolean updateDept(Dept dept) {
        return false;
    }

    @Override
    public boolean deleteDeptById(int depId) {
        return false;
    }
}
