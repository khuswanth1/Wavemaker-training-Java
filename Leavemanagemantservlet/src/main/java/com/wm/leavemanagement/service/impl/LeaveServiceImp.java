package com.wm.leavemanagement.service.impl;

import com.wm.leavemanagement.repository.LeaveRepository;
import com.wm.leavemanagement.repository.LeaveTypeRepository;
import com.wm.leavemanagement.service.LeaveService;
import com.wm.leavemanagement.pojo.*;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.leaveTypeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.repository.impl.*;
import com.wm.leavemanagement.repository.EmployeeRepository;
import com.wm.leavemanagement.pojo.LeaveRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class LeaveServiceImp implements LeaveService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveServiceImp.class);
    LeaveRepository leaveRepository = LeaveRepositoryImpl.getInstance();
    LeaveTypeRepository leaveTypeRepository = LeaveTypeRepositoryImpl.getInstance();
    EmployeeRepository employeeRepository = EmployeeRepositoryImpl.getInstance();

    @Override
    public LeaveEmp create(LeaveRequest leaveRequest) throws leaveTypeNotFoundException, loginNotFoundException {

        int requestLeaveCount = 0;
        if (leaveRequest.getDateFrom().equals(leaveRequest.getDateTo())) {
            requestLeaveCount = 1;
        } else {
            requestLeaveCount = (int) ChronoUnit.DAYS.between(leaveRequest.getDateFrom(), leaveRequest.getDateTo()) + 1;
        }
        logger.debug("Leave name : {}", leaveRequest.getLeaveName());
        LeaveType leaveType = leaveTypeRepository.getByName(leaveRequest.getLeaveName());
        Employee employee = employeeRepository.getById(leaveRequest.getAppliedBy());
        if (!leaveType.getGender().equals("ALL")) {
            if (!leaveType.getGender().equals(employee.getGender())) {
                throw new RuntimeException("This leave type is only for this : " + leaveType.getGender());
            }
        }
        int leaveCount = leaveRepository.getLeaveCountByUserId(leaveRequest.getAppliedBy(), leaveType.getId());
        if (leaveType.getCount() == leaveCount) {
            logger.debug("No leaves left for you...");
            throw new RuntimeException("You have no leaves left of : " + leaveType.getName());
        }
        if ((leaveCount + requestLeaveCount) > leaveType.getCount()) {
            throw new RuntimeException("You requested for :" + requestLeaveCount + " which exceeds leaves left for you");
        }
        leaveRequest.setLeaveId(leaveType.getId());
        return leaveRepository.create(leaveRequest);
    }

    @Override
    public List<Leave> getMyLeavesByUserId(int userId) throws loginNotFoundException {
        logger.debug("Getting my leaves by user Id: {}", userId);
        return leaveRepository.getMyLeavesByUserId(userId);
    }

    @Override
    public List<Leave> getEmployeeLeavesByManagerId(int userId) throws employeeNotFoundException, loginNotFoundException {
        logger.debug("Getting pojo Leaves By Manager Id:{}", userId);
        return leaveRepository.getEmployeeLeavesByManagerId(userId);
    }

    @Override
    public void approveLeave(int leaveId) {
        logger.debug("Approving leave id: {}", leaveId);
        leaveRepository.approveLeave(leaveId);
    }

    @Override
    public void rejectLeave(int leaveId) {
        logger.debug("Rejecting leave id: {}", leaveId);
        leaveRepository.rejectLeave(leaveId);
    }

    @Override
    public List<LeaveTracker> getLeavesTrackerByUserId(int userId) {
        logger.debug("Getting Leave tracker details of user Id:{}", userId);
        return leaveRepository.getLeaveTrackerByUserId(userId);
    }
}