package Service.Emp;

import Factory.AddressRepositoryFactory;
import Factory.EmployeeRepositoryFactory;
import Repository.AddressRepository;
import Repository.EmployeeRepository;
import Service.Emp_service;
import com.pack.Address;
import com.pack.Employee;

import java.util.List;

public class EmployeeServiceEmp implements Emp_service {

    private static EmployeeRepository employeeRepository;
    private static AddressRepository addressRepository;

    public EmployeeServiceEmp(int storageInput) {
        employeeRepository = EmployeeRepositoryFactory.getEmployeeRepositoryInstance(storageInput);
        addressRepository = AddressRepositoryFactory.getAddressRepositoryInstance(storageInput);
    }

    @Override
    public boolean addEmpId(Employee employee) {
        if (employee.getAddress() != null) {
            addressRepository.addAdress(employee.getAddress());
        }
        return employeeRepository.addEmpId(employee);

    }

    @Override
    public boolean deleteEmp(int empId) {
        if (addressRepository.getAddressByEmpId(empId) != null)
            addressRepository.deleteAdressByEmpId(empId);
        return employeeRepository.deleteEmp(empId);
    }

    @Override
    public List<Employee> getAllEmp() {
        List<Employee> employeeList = employeeRepository.getAllEmp();
        if (employeeList == null || employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return employeeList; // Returning empty list if no employees are found
        }

        for (Employee employee : employeeList) {
            if (employee == null) {
                System.out.println("Found null employee in the list, skipping...");
                continue;
            }
            try {
                Address address = addressRepository.getAddressByEmpId(employee.getEmpId());
                if (address != null) {
                    employee.setAddress(address);
                } else {
                    System.out.println("No address found for Employee ID: " + employee.getEmpId());
                }
            } catch (Exception e) {
                System.out.println("Error retrieving address for Employee ID: " + employee.getEmpId());
                e.printStackTrace();
            }
        }
        return employeeList;
    }


    @Override
    public Employee getEmpId(int id) {
        Employee emp = employeeRepository.getEmpId(id);
        if (emp != null) {  // Check if employee exists
            emp.setAddress(addressRepository.getAddressByEmpId(emp.getEmpId()));
        }
        return emp;
    }

    @Override
    public boolean updateEmp(Employee employee) {
        employeeRepository.updateEmp(employee);
        if (employee.getAddress() == null) {
            addressRepository.updateAdress(employee.getAddress());
        }
        return true;
    }

}