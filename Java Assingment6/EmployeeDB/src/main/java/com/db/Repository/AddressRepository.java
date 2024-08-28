package com.db.Repository;
import com.db.com.pack.Address;


public interface AddressRepository {
    Address getAddressByEmpId(int empId);
    boolean addAddress(Address address);
    boolean deleteAddress(int empId);
    Address deleteAddressByEmpId(int empId);



    Address updateAddress(String address);

    Address updateAddress(Address address);
}