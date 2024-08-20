package Service.Emp;

import Factory.AddressRepositoryFactory;
import Service.AddressService;
import com.pack.Address;
import Repository.AddressRepository;


public class AddressServiceEmp implements AddressService
{
    private static AddressRepository addressRepository;

    public AddressServiceEmp (int storageInput){
        addressRepository = AddressRepositoryFactory.getAddressRepositoryInstance(storageInput);
    }

    @Override
    public Address getAddressById(int id) {
        return null;
    }

    @Override
    public boolean addAddress(Address address) {
        addressRepository.addAdress(address);
        return true;
    }

    @Override
    public boolean deleteAddressByEmpId(int empId) {
        addressRepository.deleteAdressByEmpId(empId);
        return true;
    }

    @Override
    public Address getAddressByEmpId(int empId) {
        return addressRepository.getAddressByEmpId(empId);
    }

    @Override
    public boolean addAddressToEmp(int empId, Address address) {
        return false;
    }

    @Override
    public boolean updateAddress(Address address) {
        addressRepository.updateAdress(address);
        return true;
    }
}
