package com.db.Service.Emp;

import com.db.Factory.AddressRepositoryFactory;
import com.db.com.pack.Address;
import com.db.Repository.AddressRepository;
import com.db.Service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AddressServiceEmp implements AddressService {
    private final AddressRepository addressRepository;
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceEmp.class);
    private final List<Address> addressList = new ArrayList<>();

    public AddressServiceEmp(int option) {
        logger.info("Initializing AddressServiceImpl with option: {}", option);
        addressRepository = AddressRepositoryFactory.getAddressRepositoryInstance(option);
    }

    @Override
    public Address getAddressByEmpId(int empId) {
        logger.info("Fetching address for employee ID: {}", empId);
        Address address = addressRepository.getAddressByEmpId(empId);
        if (address != null) {
            logger.info("Address found for employee ID: {}", empId);
        } else {
            logger.warn("No address found for employee ID: {}", empId);
        }
        return address;
    }

    @Override
    public boolean addAddress(Address address) {
        logger.info("Adding new address for employee ID: {}", address.getEmpId());
        boolean result = addressRepository.addAddress(address);
        logger.info("Address added successfully: {}", result);
        return result;
    }

    @Override
    public Address deleteAddressByEmpId(int empId) {
        Iterator<Address> iterator = addressList.iterator();
        Address deletedAddress = null;

        while (iterator.hasNext()) {
            Address currentAddress = iterator.next();
            if (currentAddress.getEmpId() == empId) {
                deletedAddress = currentAddress;
                iterator.remove();
                logger.info("Address for employee ID {} removed successfully.", empId);
                break;
            }
        }

        if (deletedAddress == null) {
            logger.warn("No address found for employee ID {}", empId);
        }

        return deletedAddress;
    }


    @Override
    public Address updateAddress(String address) {
        logger.info("Updating address for employee ID: {}", address.isEmpty());
        Address updatedAddress = addressRepository.updateAddress(address);
        if (updatedAddress == null) {
            logger.info("Address updated successfully for employee ID: {}", address.isEmpty());
        } else {
            logger.warn("Failed to update address for employee ID: {}", address.isEmpty());
        }
        return updatedAddress;
    }
}