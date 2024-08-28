package com.db.Service;

import com.db.com.pack.Address;

import java.sql.SQLException;

public interface AddressService {

    Address getAddressById(int id);

    boolean addAddress(Address address) throws SQLException;

    Address updateAddress(String address) throws SQLException;

    Address deleteAddressByEmpId(int empId);

    Address getAddressByEmpId(int empId);

    boolean addAddressToEmp(int empId, String address);
}

