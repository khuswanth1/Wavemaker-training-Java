package Repository;
import com.pack.Address;


public interface AddressRepository {
    public boolean addAdress(Address address);
    public boolean updateAdress(Address address);
    public boolean deleteAdressByEmpId(int empId);
    public Address getAddressByEmpId(int empId);
}