package com.db.Factory.Singleton;

import com.db.Repository.EmployeeRepository;
import com.db.Repository.Emp.InDatabaseEmployeeRepositoryEmp;
import com.db.Repository.Emp.InFileEmployeeRepositoryEmp;

public class Singleton_Employee_Repository {

    private static volatile EmployeeRepository inMemoryRepository;
    private static volatile EmployeeRepository inFileRepository;
    private static volatile EmployeeRepository inDatabaseRepository;

    private Singleton_Employee_Repository() {
        // Private constructor to prevent instantiation
    }

    public static EmployeeRepository getInMemoryEmployeeRepositoryInstance() {
        if (inMemoryRepository == null) {
            synchronized (Singleton_Employee_Repository.class) {
                if (inMemoryRepository == null) {
                    inMemoryRepository = new InFileEmployeeRepositoryEmp();
                }
            }
        }
        return inMemoryRepository;
    }

    public static EmployeeRepository getInFileEmployeeRepositoryInstance() {
        if (inFileRepository == null) {
            synchronized (Singleton_Employee_Repository.class) {
                if (inFileRepository == null) {
                    inFileRepository = new InFileEmployeeRepositoryEmp();
                }
            }
        }
        return inFileRepository;
    }

    public static EmployeeRepository getInDbEmployeeRepositoryInstance() {
        if (inDatabaseRepository == null) {
            synchronized (Singleton_Employee_Repository.class) {
                if (inDatabaseRepository == null) {
                    inDatabaseRepository = new InDatabaseEmployeeRepositoryEmp();
                }
            }
        }
        return inDatabaseRepository;
    }
}
