package com.db.Factory;

import com.db.Exception.FileCreationException;
import com.db.Factory.Singleton.Singleton_Dept_Repository;
import com.db.Repository.DeptRepository;

import java.util.HashMap;
import java.util.Map;

public class DeptRepositoryFactory {
    private static final Map<Integer, DeptRepository> repositoryMap = new HashMap<>();
    private static DeptRepository deptRepository;

    // Method to get DeptRepository instance based on storage type
    public static DeptRepository getDeptRepositoryInstance(int storageType) throws FileCreationException {
        if (repositoryMap.containsKey(storageType)) {
            return repositoryMap.get(storageType);
        }

        if (storageType == 1) {
            // Get in-file repository instance
            deptRepository = Singleton_Dept_Repository.getInFileDeptRepositoryInstance();
        } else if (storageType == 2) {
            // Get in-memory repository instance
            deptRepository = Singleton_Dept_Repository.getInMemoryDeptRepositoryInstance();
        } else {
            throw new FileCreationException("Invalid storage type");
        }

        repositoryMap.put(storageType, deptRepository);
        return deptRepository;
    }
}
