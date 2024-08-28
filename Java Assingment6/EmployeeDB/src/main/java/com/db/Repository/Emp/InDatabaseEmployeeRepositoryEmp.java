package com.db.Repository.Emp;


import com.db.com.pack.Employee;
import com.db.Repository.EmployeeRepository;
import com.db.util.DatabaseConnects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class InDatabaseEmployeeRepositoryEmp implements EmployeeRepository {
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseEmployeeRepositoryEmp.class);

    @Override
    public Employee getEmployeeById(int empId) {
        String query = "SELECT * FROM EMPLOYEES WHERE EMP_ID = ?";
        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, empId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToEmployee(resultSet);
            }

        } catch (SQLException e) {
            logger.error("Error fetching employee by ID", e);
        }
        return null;
    }

    @Override
    public int addEmployee(Employee employee) {
        String query = "INSERT INTO EMPLOYEE (EMPNAME, PHONENO, EMAIL, ADDRESS) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, employee.getEmpName());
            preparedStatement.setString(2, employee.getPhoneNo());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getAddress());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Returns the auto-generated EMP_ID
                    }
                }
            }

        } catch (SQLException e) {
            logger.error("Error adding employee", e);
        }
        return -1;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEES";

        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapResultSetToEmployee(resultSet));
            }

        } catch (SQLException e) {
            logger.error("Error fetching all employees", e);
        }

        return employees;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE EMPLOYEE SET EMPNAME = ?, PHONENO = ?, EMAIL = ?, ADDRESS = ? WHERE EMP_ID = ?";
        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employee.getEmpName());
            preparedStatement.setString(2, employee.getPhoneNo());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setInt(5, employee.getEmpId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            logger.error("Error updating employee", e);
        }
        return false;
    }

    @Override
    public boolean deleteEmployeeByEmpId(int empId) {
        String query = "DELETE FROM EMPLOYEES WHERE EMP_ID = ?";
        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, empId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            logger.error("Error deleting employee", e);
        }
        return false;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        String query = "SELECT * FROM EMPLOYEES WHERE EMAIL = ?";
        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToEmployee(resultSet);
            }

        } catch (SQLException e) {
            logger.error("Error fetching employee by email", e);
        }
        return null;
    }

    private Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpId(resultSet.getInt("EMP_ID"));
        employee.setEmpName(resultSet.getString("EMPNAME"));
        employee.setPhoneNo(resultSet.getString("PhonoNo"));
        employee.setEmail(resultSet.getString("EMAIL"));
        employee.setAddress(resultSet.getString("Address"));
        return employee;
    }
}
