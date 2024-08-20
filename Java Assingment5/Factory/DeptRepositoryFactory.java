package Factory;

import Exception.FileCreationException;
import Factory.Singleton.Singleton_Dept_Repository;
import Repository.DeptRepository;

import java.util.HashMap;
import java.util.Map;

public class DeptRepositoryFactory {
    private static DeptRepository deptRepository;
    private static final Map<Integer, DeptRepository> repositoryMap = new HashMap<>();

    public static DeptRepository getDeptRepositoryInstance(int storageType)
            throws FileCreationException {
        if (repositoryMap.containsKey(storageType)) {
            return repositoryMap.get(storageType);
        }
        if (storageType == 1) {
            deptRepository = Singleton_Dept_Repository.getInFileDeptRepositoryInstance();
            repositoryMap.put(storageType, deptRepository);
        } else if (storageType == 2) {
            deptRepository = Singleton_Dept_Repository.getInMemoryDeptRepositoryInstance();
            repositoryMap.put(storageType, deptRepository);
        }
        return repositoryMap.get(storageType);
    }
}
