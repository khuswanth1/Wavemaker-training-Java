package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.Employee;
import com.wm.Leavemanagement.Exception.EmployeeNotFoundException;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import com.wm.Leavemanagement.Impl.EmployeeRepositoryImpl;
import com.wm.Leavemanagement.Repository.EmployeeRepository;
import com.wm.Leavemanagement.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImp.class);
    EmployeeRepository employeeRepository = EmployeeRepositoryImpl.getInstance();

    @Override
    public Employee getByUserName(String userName) throws LoginNotFoundException {
        logger.debug("Getting Employee details by userName: {}", userName);
        return employeeRepository.getByUserName(userName);
    }
    @Override
    public Employee getById(int userId) throws LoginNotFoundException {
        logger.debug("Getting Employee Details by user id: {}", userId);
        return employeeRepository.getById(userId);
    }
    @Override
    public List<Employee> getEmployeeByUserId(int userId) throws EmployeeNotFoundException {
        logger.debug("Getting Employee Details under this manager Id: {}", userId);
        return employeeRepository.getEmployeesByManagerId(userId);
    }
}