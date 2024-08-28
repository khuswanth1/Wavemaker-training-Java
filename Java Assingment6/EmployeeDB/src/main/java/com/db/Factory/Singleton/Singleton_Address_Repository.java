package com.db.Factory.Singleton;

import com.db.Repository.AddressRepository;
import com.db.Repository.Emp.InFileAddressRepositoryEmp;
import com.db.Repository.Emp.InMemoryAddressEmployeeEmp;
import com.db.Repository.Emp.InDatabaseAddressRepositoryEmp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Singleton_Address_Repository {

    private static volatile AddressRepository inMemoryRepository;
    private static volatile AddressRepository inFileRepository;
    private static volatile AddressRepository inDatabaseRepository;
    private static final Logger logger = LoggerFactory.getLogger(Singleton_Address_Repository.class);

    public static AddressRepository getInMemoryAddressRepositoryInstance() {
        if (inMemoryRepository == null) {
            synchronized (Singleton_Address_Repository.class) {
                if (inMemoryRepository == null) {
                    logger.info("Creating InMemoryAddressEmployeeEmp instance");
                    inMemoryRepository = new InMemoryAddressEmployeeEmp();
                }
            }
        }
        return inMemoryRepository;
    }

    public static AddressRepository getInFileAddressRepositoryInstance() {
        if (inFileRepository == null) {
            synchronized (Singleton_Address_Repository.class) {
                if (inFileRepository == null) {
                    logger.info("Creating InFileAddressRepositoryEmp instance");
                    inFileRepository = (AddressRepository) new InFileAddressRepositoryEmp();
                }
            }
        }
        return inFileRepository;
    }

    public static AddressRepository getInDatabaseAddressRepositoryInstance() {
        if (inDatabaseRepository == null) {
            synchronized (Singleton_Address_Repository.class) {
                if (inDatabaseRepository == null) {
                    logger.info("Creating InDatabaseAddressRepositoryEmp instance");
                    inDatabaseRepository = new InDatabaseAddressRepositoryEmp() {
                        @Override
                        public boolean deleteAddress(int empId) {
                            logger.info("deleteAddress called with empId: {}", empId);
                            // Implementation of deleteAddress
                            return false;
                        }
                    };
                }
            }
        }
        return inDatabaseRepository;
    }
}
