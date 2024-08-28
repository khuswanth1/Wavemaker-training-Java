package com.db.Repository.Emp;

import com.db.Repository.DeptRepository;
import com.db.com.pack.Dept;

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
