package Service;

import com.pack.Address;

public interface AddressService {
    public Address getAddressById(int id);

    public boolean addAddress(Address address);

    public boolean updateAddress(Address address);

    public boolean deleteAddressByEmpId(int empId);

    public Address getAddressByEmpId(int empId);

    public boolean addAddressToEmp(int empId, Address address);
}

