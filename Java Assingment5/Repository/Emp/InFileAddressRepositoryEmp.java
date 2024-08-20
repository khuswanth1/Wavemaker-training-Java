package Repository.Emp;

import com.pack.Address;
import Repository.AddressRepository;

public class InFileAddressRepositoryEmp implements AddressRepository {

    @Override
    public boolean addAdress(Address address) {
        return false;
    }

    @Override
    public Address getAddressByEmpId(int empId) {
        return null;
    }

    @Override
    public boolean deleteAdressByEmpId(int empId) {
        return false;
    }

    @Override
    public boolean updateAdress(Address address) {
        return false;
    }

}