package Factory.Singleton;
import Factory.EmployeeRepositoryFactory;
import Repository.EmployeeRepository;
import Repository.Emp.InFileEmployeeRepositoryEmp;
import Repository.Emp.InMemoryEmployeeRepositoryEmp;

public class Singleton_Employee_Repository extends EmployeeRepositoryFactory {

    private static  EmployeeRepository inMemoryRepository = null;
    private static  EmployeeRepository inFileRepository = null;

    public static EmployeeRepository getInMemoryEmployeeRepositoryInstance() {
        if (inMemoryRepository == null) {
            synchronized (Singleton_Employee_Repository.class) {
                if (inMemoryRepository == null) {
                    inMemoryRepository = new InMemoryEmployeeRepositoryEmp();
                }
            }
        }
        return inMemoryRepository;
    }

    public static EmployeeRepository getInFileEmployeeRepositoryInstance(){
        if (inFileRepository == null) {
            synchronized (Singleton_Employee_Repository.class) {
                if (inFileRepository == null) {
                    inFileRepository = (EmployeeRepository) new InFileEmployeeRepositoryEmp();
                }
            }
        }
        return inFileRepository;
    }
}


