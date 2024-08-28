package controller;

import com.pack.Address;
import com.pack.Employee;
import com.pack.Dept;
import Exception.DuplicateEmployeeRecordException;
import Exception.EmployeeNotFoundException;
import Service.AddressService;
import Service.Emp_service;
import Service.DeptService;
import Service.Emp.AddressServiceEmp;
import Service.Emp.EmployeeServiceEmp;
import Service.Emp.DeptServiceEmp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EmpController {
    private static Emp_service employeeService;
    private static AddressService addressService;
    private static DeptService deptService;
    private static String filePath = "data/employees.txt";

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int storageInput = 1;

        try {
            storageInput = fetchStorageInput(sc);
            if (storageInput == 1) {
                System.out.println("Enter In-Memory path for storage:");
                filePath = sc.nextLine();
            }
        }

        catch (IOException e) {
            System.out.println("Invalid input provided: " + e.getMessage());
            return;
        }

        try {
            storageInput = fetchStorageInput(sc);
            if (storageInput == 2) {
                System.out.println("Enter file path for storage:");
                filePath = sc.nextLine();
            }
        } catch (IOException e) {
            System.out.println("Invalid input provided: " + e.getMessage());
            return;
        }

        employeeService = new EmployeeServiceEmp(storageInput);
        addressService = new AddressServiceEmp(storageInput);
        deptService = new DeptServiceEmp(storageInput);

        boolean check = true;
        while (check) {
            System.out.println("1. Add Employee");
            System.out.println("2. Get Employee by ID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Update Employee Address");
            System.out.println("7. Add Department");
            System.out.println("8. Get Department by ID");
            System.out.println("9. Get All Departments");
            System.out.println("10. Store all employees");
            System.out.println("11. Exit");

            int menuChoice = sc.nextInt();
            sc.nextLine();

            switch (menuChoice) {
                case 1:
                    handleAddEmployee(sc);
                    break;
                case 2:
                    handleGetEmployeeById(sc);
                    break;
                case 3:
                    handleGetAllEmployees();
                    break;
                case 4:
                    handleUpdateEmployee(sc);
                    break;
                case 5:
                    handleDeleteEmployee(sc);
                    break;
                case 6:
                    handleUpdateEmployeeAddress(sc);
                    break;
                case 7:
                    handleAddDepartment(sc);
                    break;
                case 8:
                    handleGetDepartmentById(sc);
                    break;
                case 9:
                    handleGetAllDepartments(sc);
                    break;
                case 10:
                    handleStoreAllEmployees();
                    break;
                case 11:
                    check = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        sc.close();
    }

    private static void handleAddEmployee(Scanner sc) {
        Employee employee = new Employee();
        System.out.println("Enter employee ID:");
        employee.setEmpId(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter employee Name:");
        employee.setEmpName(sc.nextLine());
        System.out.println("Enter phone number:");
        employee.setPhoneNo(sc.nextLine());
        System.out.println("Enter email:");
        employee.setEmail(sc.nextLine());

        System.out.println("Would you like to add an address? (1 for Yes, 0 for No):");
        if (sc.nextInt() == 1) {
            sc.nextLine();
            Address address = new Address();
            System.out.println("Enter EmpId:");
            address.setEmpId(sc.nextInt());
            sc.nextLine();
            System.out.println("Enter Country:");
            address.setCountry(sc.nextLine());
            System.out.println("Enter state:");
            address.setState(sc.nextLine());
            System.out.println("Enter city:");
            address.setCity(sc.nextLine());
            System.out.println("Enter location:");
            address.setLocation(sc.nextLine());
            System.out.println("Enter pincode:");
            address.setPin(sc.nextInt());
            sc.nextLine();
            employee.setAddress(address);
        }

        try {
            employeeService.addEmpId(employee);
        } catch (DuplicateEmployeeRecordException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleGetEmployeeById(Scanner sc) {
        System.out.println("Enter employee ID:");
        int empId = sc.nextInt();
        try {
            Employee employee = employeeService.getEmpId(empId);
            System.out.println(employee);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmp();
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        employees.forEach(System.out::println);
    }

    private static void handleUpdateEmployee(Scanner sc) {
        System.out.println("Enter employee ID to update:");
        int empId = sc.nextInt();
        sc.nextLine();

        try {
           
            Employee employee = employeeService.getEmpId(empId);

            
            System.out.println("Select the detail to update:");
            System.out.println("1. Update Name");
            System.out.println("2. Update Phone Number");
            System.out.println("3. Update Email");

            int updateChoice = sc.nextInt();
            sc.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.println("Enter new Name:");
                    String newName = sc.nextLine();
                    employee.setEmpName(newName);
                    break;
                case 2:
                    System.out.println("Enter new Phone Number:");
                    String newPhoneNo = sc.nextLine();
                    employee.setPhoneNo(newPhoneNo);
                    break;
                case 3:
                    System.out.println("Enter new Email:");
                    String newEmail = sc.nextLine();
                    employee.setEmail(newEmail);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    return;
            }
            employeeService.updateEmp(employee);
            System.out.println("Employee updated successfully.");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleDeleteEmployee(Scanner sc) {
        System.out.println("Enter employee ID to delete:");
        int empId = sc.nextInt();
        try {
            employeeService.deleteEmp(empId);
            System.out.println("Employee deleted successfully.");
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleUpdateEmployeeAddress(Scanner sc) {
        System.out.println("Enter employee ID to update address:");
        int empId = sc.nextInt();
        sc.nextLine();

        try {
            // Fetch the address by employee ID
            Address address = addressService.getAddressByEmpId(empId);
            if (address == null) {
                System.out.println("Address not found for the given employee ID.");
                return;
            }

            // Display update options for the address
            System.out.println("Select the detail to update:");
            System.out.println("1. Update Country");
            System.out.println("2. Update State");
            System.out.println("3. Update City");
            System.out.println("4. Update Location");
            System.out.println("5. Update Pincode");

            int addressUpdateChoice = sc.nextInt();
            sc.nextLine(); 
            switch (addressUpdateChoice) {
                case 1:
                    System.out.println("Enter new Country:");
                    String country = sc.nextLine();
                    address.setCountry(country);
                    break;
                case 2:
                    System.out.println("Enter new State:");
                    String state = sc.nextLine();
                    address.setState(state);
                    break;
                case 3:
                    System.out.println("Enter new City:");
                    String city = sc.nextLine();
                    address.setCity(city);
                    break;
                case 4:
                    System.out.println("Enter new Location:");
                    String location = sc.nextLine();
                    address.setLocation(location);
                    break;
                case 5:
                    System.out.println("Enter new Pincode:");
                    int pincode = sc.nextInt();
                    address.setPin(pincode);
                    sc.nextLine();  
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    return;
            }
            addressService.updateAddress(address);
            System.out.println("Address updated successfully.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleAddDepartment(Scanner sc) {
        Dept dept = new Dept();
        System.out.println("Enter department ID:");
        dept.setDepId(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter department Name:");
        dept.setDepName(sc.nextLine());

        deptService.addDept(dept);
    }

    private static void handleGetDepartmentById(Scanner sc) {
        System.out.println("Enter department ID:");
        int depId = sc.nextInt();
        Dept dept = deptService.getDeptById(depId);
        System.out.println(dept);
    }

    private static void handleGetAllDepartments(Scanner sc) {
        List<Dept> departments = deptService.getAllDepts();
        departments.forEach(System.out::println);
    }

    private static void handleStoreAllEmployees() {
        List<Employee> employees = employeeService.getAllEmp();
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to store.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                writer.write(employee.getEmpId() + "," + employee.getEmpName() + "," + employee.getPhoneNo() + "," + employee.getEmail());
                if (employee.getAddress() != null) {
                    Address address = employee.getAddress();
                    writer.write("," + address.getCountry() + "," + address.getState() + "," + address.getCity() + "," + address.getLocation() + "," + address.getPin());
                }
                writer.newLine();
            }
            System.out.println("All employees have been stored successfully.");
        } catch (IOException e) {
            System.out.println("Failed to store employees: " + e.getMessage());
        }
    }

    private static int fetchStorageInput(Scanner sc) throws IOException {
        System.out.println("Choose storage type:");
        System.out.println("1. In-Memory Storage");
        System.out.println("2. File Storage");

        int storageInput = sc.nextInt();
        sc.nextLine();

        if (storageInput != 1 && storageInput != 2) {
            throw new IOException("Invalid input. Please choose 1 for In-Memory Storage or 2 for File Storage.");
        }

        return storageInput;
    }
}
