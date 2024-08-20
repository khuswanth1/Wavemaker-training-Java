package Factory;

import Exception.FileCreationException;
import Factory.Singleton.Singleton_Employee_Repository;
import Repository.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepositoryFactory {
    private static EmployeeRepository employeeRepository;
    private static final Map<Integer, EmployeeRepository> repositoryMap = new HashMap<>();

    public static EmployeeRepository getEmployeeRepositoryInstance(int storageType)
            throws FileCreationException{
        if (repositoryMap.containsKey(storageType)) {
            return repositoryMap.get(storageType);
        }
        if (storageType == 1 ) {
            employeeRepository = Singleton_Employee_Repository.getInFileEmployeeRepositoryInstance();
            repositoryMap.put(storageType, employeeRepository);
        } else if (storageType == 2) {
            employeeRepository = Singleton_Employee_Repository.getInMemoryEmployeeRepositoryInstance();
            repositoryMap.put(storageType, employeeRepository);

        }
        return repositoryMap.get(storageType);
    }
}