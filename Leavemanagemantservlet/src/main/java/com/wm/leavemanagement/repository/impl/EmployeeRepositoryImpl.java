package com.wm.leavemanagement.repository.impl;

import com.wm.leavemanagement.pojo.Employee;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.repository.EmployeeRepository;
import com.wm.leavemanagement.util.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
    private static final String GET_EMPLOYEE = "SELECT * FROM EMPLOYEE WHERE USER_NAME = ?";
    private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM EMPLOYEE WHERE ID = ?";
    private static final String GET_EMPLOYEES_BY_MANAGER_ID = "SELECT * FROM EMPLOYEE WHERE MANAGER_ID = ?";
    private static EmployeeRepositoryImpl instance;

    private EmployeeRepositoryImpl() {
    }

    public static synchronized EmployeeRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Employee getByUserName(String userName) throws loginNotFoundException {
        logger.debug("Getting pojo Details by userName:");
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = DatabaseConnector.connect().prepareStatement(GET_EMPLOYEE);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean flag = resultSet.next();
            if (flag) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getDate("DOB").toLocalDate());
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setGender(resultSet.getString("gender"));
                employee.setUserName(resultSet.getString("user_name"));
                employee.setManagerId(resultSet.getInt("manager_id"));
            }
        } catch (SQLException e) {
            throw new loginNotFoundException("No user found");
        }
        return employee;
    }

    @Override
    public Employee getById(int userId) throws loginNotFoundException {
        logger.debug("Getting pojo Details by userId:");
        Employee employee = new Employee();
        try {
            PreparedStatement preparedStatement = DatabaseConnector.connect().prepareStatement(GET_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean flag = resultSet.next();
            if (flag) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getDate("DOB").toLocalDate());
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setGender(resultSet.getString("gender"));
                employee.setUserName(resultSet.getString("user_name"));
                employee.setManagerId(resultSet.getInt("manager_id"));
            }
        } catch (SQLException e) {
            throw new loginNotFoundException("No user found");
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployeesByManagerId(int userId) throws employeeNotFoundException {
        logger.debug("Getting pojo Details by userId: {}", userId);

        List<Employee> employeeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnector.connect().prepareStatement(GET_EMPLOYEES_BY_MANAGER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean flag = resultSet.next();
            while (flag) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getDate("DOB").toLocalDate());
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setGender(resultSet.getString("gender"));
                employee.setUserName(resultSet.getString("user_name"));
                employee.setManagerId(resultSet.getInt("manager_id"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            throw new employeeNotFoundException("No user found");
        }
        return employeeList;
    }
}