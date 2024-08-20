package Factory.Singleton;

import Factory.DeptRepositoryFactory;
import Repository.DeptRepository;
import Repository.Emp.InFileDeptRepositoryEmp;
import Repository.Emp.InMemoryDeptRepositoryEmp;

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
