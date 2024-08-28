package com.db.Factory;

import com.db.Exception.FileCreationException;
import com.db.Factory.Singleton.Singleton_Employee_Repository;
import com.db.Repository.EmployeeRepository;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeRepositoryFactory {
    private static final Map<Integer, EmployeeRepository> repositoryMap = new HashMap<>();
    private static Connection connection;
    public static EmployeeRepository getEmployeeRepositoryInstance(int storageType) throws FileCreationException {
        if (repositoryMap.containsKey(storageType)) {
            return repositoryMap.get(storageType);
        }

        EmployeeRepository employeeRepository;
        if (storageType == 1) {
            employeeRepository = Singleton_Employee_Repository.getInMemoryEmployeeRepositoryInstance();
        } else if (storageType == 2) {

            employeeRepository = Singleton_Employee_Repository.getInFileEmployeeRepositoryInstance();
        } else if (storageType == 3) {

            if (connection == null) {
                throw new FileCreationException("Database connection is required for database storage type.");
            }
            employeeRepository = Singleton_Employee_Repository.getInDbEmployeeRepositoryInstance();
        } else {
            throw new FileCreationException("Invalid storage type");
        }

        repositoryMap.put(storageType, employeeRepository);
        return employeeRepository;
    }

    public static EmployeeRepository getEmployeeRepositoryInstance(Connection connection) {
        if (connection != null) {
            setConnection(connection);
            return Singleton_Employee_Repository.getInDbEmployeeRepositoryInstance();
        }
        return null;
    }
    public static void setConnection(Connection connection) {
        EmployeeRepositoryFactory.connection = connection;
    }
    public static Connection getConnection() {
        return connection;
    }
}
