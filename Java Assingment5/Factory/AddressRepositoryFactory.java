package Factory;

import java.util.HashMap;
import java.util.Map;

import Exception.FileCreationException;
import Factory.Singleton.Singleton_Address_Repository;
import Repository.AddressRepository;

public class AddressRepositoryFactory {
    private static AddressRepository addressRepository;
    private static final Map<Integer, AddressRepository> repositoryMap = new HashMap<>();

    public static AddressRepository getAddressRepositoryInstance(int storageType)
            throws FileCreationException{
        if (repositoryMap.containsKey(storageType)) {
            return repositoryMap.get(storageType);
        }
        if (storageType == 1 ) {
            addressRepository = Singleton_Address_Repository.getInFileAddressRepositoryInstance();
            repositoryMap.put(storageType, addressRepository);
        } else if (storageType == 2) {
            addressRepository = Singleton_Address_Repository.getInMemoryAddressRepositoryInstance();
            repositoryMap.put(storageType, addressRepository);

        }
        return repositoryMap.get(storageType);
    }
}