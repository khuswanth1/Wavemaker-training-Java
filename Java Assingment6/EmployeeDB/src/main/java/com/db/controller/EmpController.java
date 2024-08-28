/*package com.db.controller;

import com.db.com.pack.Address;
import com.db.com.pack.Employee;
import com.db.com.pack.Dept;
import com.db.Exception.DuplicateEmployeeRecordException;
import com.db.Exception.EmployeeNotFoundException;
import com.db.Service.AddressService;
import com.db.Service.Emp_service;
import com.db.Service.DeptService;
import com.db.Service.Emp.AddressServiceEmp;
import com.db.Service.Emp.EmployeeServiceEmp;
import com.db.Service.Emp.DeptServiceEmp;
import com.db.util.DatabaseConnects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmpController {
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
    private static Emp_service employeeService;
    private static AddressService addressService;
    private static DeptService deptService;
    private static Connection connection;
    private static String filePath = "data/employees.txt";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int storageType = fetchStorageInput(sc);

            // Initialize services based on storage type
            initializeServices(storageType);

            boolean continueRunning = true;
            while (continueRunning) {
                displayMenu();
                int menuChoice = sc.nextInt();
                sc.nextLine();

                switch (menuChoice) {
                    case 1: handleAddEmployee(sc); break;
                    case 2: handleGetEmployeeById(sc); break;
                    case 3: handleGetAllEmployees(); break;
                    case 4: handleUpdateEmployee(sc); break;
                    case 5: handleDeleteEmployee(sc); break;
                    case 6: handleUpdateEmployeeAddress(sc); break;
                    case 7: handleAddDepartment(sc); break;
                    case 8: handleGetDepartmentById(sc); break;
                    case 9: handleGetAllDepartments(); break;
                    case 10: handleStoreAllEmployees(); break;
                    case 11: continueRunning = false; logger.info("Exiting application."); break;
                    default: logger.warn("Invalid menu choice: {}", menuChoice); System.out.println("Invalid choice! Please try again."); break;
                }
            }
        } catch (Exception e) {
            logger.error("Unexpected error occurred: ", e);
        } finally {
            closeConnection();
        }
    }

    private static void initializeServices(int storageType) {
        switch (storageType) {
            case 1: // In-Memory
                employeeService = new EmployeeServiceEmp(storageType, connection);
                addressService = new AddressServiceEmp(storageType, connection) {

                    @Override
                    public Address getAddressById(int id) {
                        return null;
                    }


                    @Override
                    public boolean addAddressToEmp(int empId, Address address) {
                        return false;
                    }
                };
                deptService = new DeptServiceEmp(storageType) {

                    @Override
                    public boolean addDept(Dept dept) {
                        return false;
                    }


                    @Override
                    public boolean updateDept(Dept dept) {
                        return false;
                    }


                    @Override
                    public boolean deleteDeptById(int depId) {
                        return false;
                    }
                };
                break;
            case 2: // File
                System.out.println("Enter file path for storage:");
                filePath = new Scanner(System.in).nextLine();
                employeeService = new EmployeeServiceEmp(storageType, connection);
                addressService = new AddressServiceEmp(storageType, connection) {

                    @Override
                    public Address getAddressById(int id) {
                        return null;
                    }


                    @Override
                    public boolean addAddressToEmp(int empId, Address address) {
                        return false;
                    }
                };
                deptService = new DeptServiceEmp(storageType) {

                    @Override
                    public boolean addDept(Dept dept) {
                        return false;
                    }


                    @Override
                    public boolean updateDept(Dept dept) {
                        return false;
                    }


                    @Override
                    public boolean deleteDeptById(int depId) {
                        return false;
                    }
                };
                break;
            case 3: // Database
                try {
                    connection = DatabaseConnects.getConnection();
                    employeeService = new EmployeeServiceEmp(storageType, connection);
                    addressService = new AddressServiceEmp(storageType, connection) {

                        @Override
                        public Address getAddressById(int id) {
                            return null;
                        }


                        @Override
                        public boolean addAddressToEmp(int empId, Address address) {
                            return false;
                        }
                    };
                    deptService = new DeptServiceEmp(storageType, connection) {

                        @Override
                        public boolean addDept(Dept dept) {
                            return false;
                        }

                        @Override
                        public boolean updateDept(Dept dept) {
                            return false;
                        }

                        @Override
                        public boolean deleteDeptById(int depId) {
                            return false;
                        }
                    };
                    logger.info("Initialized services with MySQL database.");
                } catch (SQLException e) {
                    logger.error("Failed to connect to MySQL database", e);
                    throw new RuntimeException(e);
                }
                break;
            default:
                logger.error("Invalid storage type selected: {}", storageType);
                System.out.println("Invalid storage type selected.");
                throw new IllegalArgumentException("Invalid storage type");
        }
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed.");
            } catch (SQLException e) {
                logger.error("Failed to close database connection", e);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add Employee");
        System.out.println("2. Get Employee by ID");
        System.out.println("3. Get All Employees");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Update Employee Address");
        System.out.println("7. Add Department");
        System.out.println("8. Get Department by ID");
        System.out.println("9. Get All Departments");
        System.out.println("10. Store All Employees");
        System.out.println("11. Exit");
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
            System.out.println("Enter Country:");
            address.setCountry(sc.nextLine());
            System.out.println("Enter State:");
            address.setState(sc.nextLine());
            System.out.println("Enter City:");
            address.setCity(sc.nextLine());
            System.out.println("Enter Location:");
            address.setLocation(sc.nextLine());
            System.out.println("Enter Pincode:");
            address.setPin(sc.nextInt());
            sc.nextLine();
            employee.setAddress(String.valueOf(address));
        }

        try {
            employeeService.addEmployee(employee);
            logger.info("Added employee: {}", employee);
            System.out.println("Employee added successfully.");
        } catch (DuplicateEmployeeRecordException e) {
            logger.error("Failed to add employee due to duplicate record", e);
            System.out.println(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while adding employee", e);
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleGetEmployeeById(Scanner sc) {
        System.out.println("Enter employee ID:");
        int empId = sc.nextInt();
        try {
            Employee employee = employeeService.getEmployeeById(empId);
            System.out.println(employee);
            logger.info("Fetched employee by ID: {}", empId);
        } catch (EmployeeNotFoundException e) {
            logger.error("Employee not found: {}", empId, e);
            System.out.println(e.getMessage());
        }
    }

    private static void handleGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees == null || employees.isEmpty()) {
            logger.warn("No employees found.");
            System.out.println("No employees found.");
            return;
        }
        employees.forEach(System.out::println);
        logger.info("Fetched all employees, count: {}", employees.size());
    }

    private static void handleUpdateEmployee(Scanner sc) {
        System.out.println("Enter employee ID to update:");
        int empId = sc.nextInt();
        sc.nextLine();

        try {
            Employee employee = employeeService.getEmployeeById(empId);

            System.out.println("Select the detail to update:");
            System.out.println("1. Update Name");
            System.out.println("2. Update Phone Number");
            System.out.println("3. Update Email");

            int updateChoice = sc.nextInt();
            sc.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.println("Enter new Name:");
                    employee.setEmpName(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new Phone Number:");
                    employee.setPhoneNo(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Enter new Email:");
                    employee.setEmail(sc.nextLine());
                    break;
                default:
                    logger.warn("Invalid update choice: {}", updateChoice);
                    System.out.println("Invalid choice! Please try again.");
                    return;
            }
            employeeService.updateEmployee(employee);
            logger.info("Updated employee: {}", employee);
            System.out.println("Employee updated successfully.");
        } catch (EmployeeNotFoundException e) {
            logger.error("Employee not found for update: {}", empId, e);
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating employee", e);
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleDeleteEmployee(Scanner sc) {
        System.out.println("Enter employee ID to delete:");
        int empId = sc.nextInt();
        try {
            employeeService.deleteEmployeeByEmpId(empId);
            logger.info("Deleted employee with ID: {}", empId);
            System.out.println("Employee deleted successfully.");
        } catch (EmployeeNotFoundException e) {
            logger.error("Employee not found for deletion: {}", empId, e);
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting employee", e);
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleUpdateEmployeeAddress(Scanner sc) {
        System.out.println("Enter employee ID to update address:");
        int empId = sc.nextInt();
        sc.nextLine();

        try {
            Employee employee = employeeService.getEmployeeById(empId);
            Address address = employee.getAddress();

            if (address == null) {
                System.out.println("No address found for this employee.");
                return;
            }

            System.out.println("Enter new Address details:");
            System.out.println("Country:");
            address.setCountry(sc.nextLine());
            System.out.println("State:");
            address.setState(sc.nextLine());
            System.out.println("City:");
            address.setCity(sc.nextLine());
            System.out.println("Location:");
            address.setLocation(sc.nextLine());
            System.out.println("Pincode:");
            address.setPin(sc.nextInt());
            sc.nextLine();

            employeeService.updateEmployee(employee);
            logger.info("Updated address for employee: {}", empId);
            System.out.println("Employee address updated successfully.");
        } catch (EmployeeNotFoundException e) {
            logger.error("Employee not found for address update: {}", empId, e);
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating employee address", e);
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleAddDepartment(Scanner sc) {
        Dept department = new Dept();
        System.out.println("Enter department ID:");
        department.setDepId(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter department Name:");
        department.setDepName(sc.nextLine());

        try {
            deptService.addDept(department);
            logger.info("Added department: {}", department);
            System.out.println("Department added successfully.");
        } catch (Exception e) {
            logger.error("Unexpected error occurred while adding department", e);
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void handleGetDepartmentById(Scanner sc) {
        System.out.println("Enter department ID:");
        int deptId = sc.nextInt();
        try {
            Dept department = deptService.getDeptById(deptId);
            System.out.println(department);
            logger.info("Fetched department by ID: {}", deptId);
        } catch (Exception e) {
            logger.error("Department not found: {}", deptId, e);
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleGetAllDepartments() {
        List<Dept> departments = deptService.getAllDepts();
        if (departments == null || departments.isEmpty()) {
            logger.warn("No departments found.");
            System.out.println("No departments found.");
            return;
        }
        departments.forEach(System.out::println);
        logger.info("Fetched all departments, count: {}", departments.size());
    }

    private static void handleStoreAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
            logger.info("Stored all employees to file: {}", filePath);
            System.out.println("All employees stored successfully.");
        } catch (IOException e) {
            logger.error("Failed to write employees to file: {}", filePath, e);
            System.out.println("An error occurred while storing employees: " + e.getMessage());
        }
    }

    private static int fetchStorageInput(Scanner sc) {
        System.out.println("Select storage type:");
        System.out.println("1. In-Memory");
        System.out.println("2. File");
        System.out.println("3. Database");
        return sc.nextInt();
    }
}
*/