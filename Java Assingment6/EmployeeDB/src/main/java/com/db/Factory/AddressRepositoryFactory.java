package com.db.Factory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.db.Exception.FileCreationException;
import com.db.Factory.Singleton.Singleton_Address_Repository;
import com.db.Repository.AddressRepository;

public abstract class AddressRepositoryFactory {
    private static Connection connection;
    private static AddressRepository addressRepository;
    private static final Map<Integer, AddressRepository> repositoryMap = new HashMap<>();

    // Overloaded method to handle cases where Connection is provided
    public static AddressRepository getAddressRepositoryInstance(Connection connection) {
        if (connection != null) {
            if (addressRepository == null) {
                // Initialize repository based on some condition or default
                addressRepository = Singleton_Address_Repository.getInDatabaseAddressRepositoryInstance();
            }
            return addressRepository;
        }
        return null;
    }
    public static AddressRepository getAddressRepositoryInstance(int storageType)
            throws FileCreationException {
        if (repositoryMap.containsKey(storageType)) {
            return repositoryMap.get(storageType);
        }
        if (storageType == 1) {
            addressRepository = Singleton_Address_Repository.getInFileAddressRepositoryInstance();
            repositoryMap.put(storageType, addressRepository);
        } else if (storageType == 2) {
            addressRepository = Singleton_Address_Repository.getInMemoryAddressRepositoryInstance();
            repositoryMap.put(storageType, addressRepository);
        } else if (storageType == 3) {
            // This would be the case where database storage is required
            addressRepository = Singleton_Address_Repository.getInDatabaseAddressRepositoryInstance();
            repositoryMap.put(storageType, addressRepository);
        } else {
            throw new FileCreationException("Invalid storage type provided: " + storageType);
        }
        return addressRepository;
    }
}
