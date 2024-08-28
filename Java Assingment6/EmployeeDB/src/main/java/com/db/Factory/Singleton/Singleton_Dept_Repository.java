package com.db.Factory.Singleton;

import com.db.Factory.DeptRepositoryFactory;
import com.db.Repository.DeptRepository;
import com.db.Repository.Emp.InFileDeptRepositoryEmp;
import com.db.Repository.Emp.InMemoryDeptRepositoryEmp;

public class Singleton_Dept_Repository extends DeptRepositoryFactory {
    private static DeptRepository inMemoryRepository = null;
    private static DeptRepository inFileRepository = null;

    public static DeptRepository getInMemoryDeptRepositoryInstance() {
        if (inMemoryRepository == null) {
            synchronized (Singleton_Dept_Repository.class) {
                if (inMemoryRepository == null) {
                    inMemoryRepository = new InMemoryDeptRepositoryEmp();
                }
            }
        }
        return inMemoryRepository;
    }

    public static DeptRepository getInFileDeptRepositoryInstance() {
        if (inFileRepository == null) {
            synchronized (Singleton_Dept_Repository.class) {
                if (inFileRepository == null) {
                    inFileRepository = new InFileDeptRepositoryEmp();
                }
            }
        }
        return inFileRepository;
    }
}
