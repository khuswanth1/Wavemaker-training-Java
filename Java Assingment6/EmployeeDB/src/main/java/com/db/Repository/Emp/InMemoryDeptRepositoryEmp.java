package com.db.Repository.Emp;

import com.db.Repository.DeptRepository;
import com.db.com.pack.Dept;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDeptRepositoryEmp implements DeptRepository{
    private static final Map<Integer, Dept> deptMap = new HashMap<>();

    @Override
    public Dept getDeptById(int id) {
        return deptMap.get(id);
    }

    @Override
    public boolean addDept(Dept dept) {
        if (!deptMap.containsKey(dept.getDepId())) {
            deptMap.put(dept.getDepId(), dept);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDept(Dept dept) {
        if (deptMap.containsKey(dept.getDepId())) {
            deptMap.put(dept.getDepId(), dept);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDeptById(int depId) {
        if (deptMap.containsKey(depId)) {
            deptMap.remove(depId);
            return true;
        }
        return false;
    }
}
