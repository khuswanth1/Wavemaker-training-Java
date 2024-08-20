package Factory.Singleton;
import Factory.AddressRepositoryFactory;
import Repository.AddressRepository;
import Repository.Emp.InFileAddressRepositoryEmp;
import Repository.Emp.InMemoryAddressEmployeeEmp;

public class Singleton_Address_Repository extends AddressRepositoryFactory {
    private static  AddressRepository inMemoryRepository = null;
    private static  AddressRepository inFileRepository = null;

    public static AddressRepository getInMemoryAddressRepositoryInstance() {
        if (inMemoryRepository == null) {
            synchronized (Singleton_Address_Repository.class) {
                if (inMemoryRepository == null) {
                    inMemoryRepository = (AddressRepository) new InMemoryAddressEmployeeEmp();
                }
            }
        }
        return inMemoryRepository;
    }

    public static AddressRepository getInFileAddressRepositoryInstance(){
        if (inFileRepository == null) {
            synchronized (Singleton_Address_Repository.class) {
                if (inFileRepository == null) {
                    inFileRepository = new InFileAddressRepositoryEmp();
                }
            }
        }
        return inFileRepository;
    }
}