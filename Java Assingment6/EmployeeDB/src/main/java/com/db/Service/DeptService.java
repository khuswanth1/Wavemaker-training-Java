package com.db.Service;

import com.db.com.pack.Dept;

import java.util.List;

public interface DeptService {

    Dept getDeptById(int id);
    boolean addDept(Dept dept);
    boolean updateDept(Dept dept);
    boolean deleteDeptById(int depId);
    String getDeptByEmpId(int empId);

    List<Dept> getAllDepts();
}
