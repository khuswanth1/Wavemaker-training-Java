package com.db.Repository.Emp;

import com.db.com.pack.Address;
import com.db.Repository.AddressRepository;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

public class InMemoryAddressEmployeeEmp implements AddressRepository {
    private static final Map<Integer, Address> addressMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(InMemoryAddressEmployeeEmp.class);

    @Override
    public Address getAddressByEmpId(int empId) {
        logger.info("Fetching address for employee ID: {}", empId);
        return addressMap.get(empId);
    }

    @Override
    public boolean addAddress(Address address) {
        logger.info("Adding address for employee ID: {}", address.getEmpId());
        int addressId = getMaxAddressId() + 1;
        address.setAddress(String.valueOf(addressId));
        addressMap.put(address.getEmpId(), address);
        logger.info("Address added for employee ID: {}", address.getEmpId());
        return true;
    }

    @Override
    public Address deleteAddressByEmpId(int empId) {
        logger.info("Deleting address for employee ID: {}", empId);
        Address deletedAddress = addressMap.remove(empId);
        logger.info("Address deleted for employee ID: {}", empId);
        return deletedAddress;
    }

    @Override
    public Address updateAddress(String address) {
        return null;
    }

    @Override
    public boolean deleteAddress(int empId) {
        return false;
    }

    @Override
    public Address updateAddress(Address address) {
        logger.info("Updating address for employee ID: {}", address.getEmpId());
        Address updatedAddress = null;
        if (addressMap.containsKey(address.getEmpId())) {
            updatedAddress = addressMap.put(address.getEmpId(), address);
            logger.info("Address updated for employee ID: {}", address.getEmpId());
        } else {
            logger.warn("Address not found for employee ID: {}, cannot update", address.getEmpId());
        }
        return updatedAddress;
    }

    private int getMaxAddressId() {
        int maxAddressId = 0;
        for (int i : addressMap.keySet()) {
            maxAddressId = Math.max(maxAddressId, i);
        }
        return maxAddressId;
    }
}