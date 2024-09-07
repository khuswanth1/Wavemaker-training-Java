package com.wm.leavemanagement.service.impl;

import com.wm.leavemanagement.pojo.Employee;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.repository.impl.EmployeeRepositoryImpl;
import com.wm.leavemanagement.repository.EmployeeRepository;
import com.wm.leavemanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);
    EmployeeRepository employeeRepository = EmployeeRepositoryImpl.getInstance();

    @Override
    public Employee getByUserName(String userName) throws loginNotFoundException {
        logger.debug("Getting pojo details by userName: {}", userName);
        return employeeRepository.getByUserName(userName);
    }

    @Override
    public Employee getById(int userId) throws loginNotFoundException {
        logger.debug("Getting pojo Details by user id: {}", userId);
        return employeeRepository.getById(userId);
    }

    @Override
    public List<Employee> getEmployeeByUserId(int userId) throws employeeNotFoundException {
        logger.debug("Getting pojo Details under this manager Id: {}", userId);
        return employeeRepository.getEmployeesByManagerId(userId);
    }
}