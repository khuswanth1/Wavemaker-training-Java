package Repository.Emp;

import java.util.*;

import com.pack.Address;
import Repository.AddressRepository;

public class InMemoryAddressEmployeeEmp implements AddressRepository{

    private static final Map<Integer,Address> addressMap = new HashMap<>();

    @Override
    public boolean addAdress(Address address) {
        if(!addressMap.containsKey(address.getEmpId())){
            addressMap.put(address.getEmpId(),address);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAdressByEmpId(int empId) {
        if(addressMap.containsKey(empId)){
            addressMap.remove(empId);
            return true;
        }
        return false;
    }

    @Override
    public Address getAddressByEmpId(int empId) {
        return addressMap.get(empId);
    }

    @Override
    public boolean updateAdress(Address address) {
        if(addressMap.containsKey(address.getEmpId())){
            addressMap.put(address.getEmpId(),address);
            return true;
        }
        return true;
    }

}