package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.*;
import com.wm.Leavemanagement.Exception.EmployeeNotFoundException;
import com.wm.Leavemanagement.Exception.LeaveTypeNotFoundException;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import com.wm.Leavemanagement.Impl.*;
import com.wm.Leavemanagement.model.LeaveRequest;
import com.wm.Leavemanagement.Repository.EmployeeRepository;

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
    public LeaveEmp create(LeaveRequest leaveRequest) throws LeaveTypeNotFoundException, LoginNotFoundException {

        int requestLeaveCount = 0;

        if (leaveRequest.getDateFrom().equals(leaveRequest.getDateTo())) {
            requestLeaveCount = 1;
        } else {
            requestLeaveCount = (int) ChronoUnit.DAYS.between(leaveRequest.getDateFrom(), leaveRequest.getDateTo()) + 1;
        }

        logger.debug("Leave name : {}" , leaveRequest.getLeaveName());
        LeaveType leaveType = leaveTypeRepository.getByName(leaveRequest.getLeaveName());

        //check if gender matches or not..
        Employee employee = employeeRepository.getById(leaveRequest.getAppliedBy());
        if(!leaveType.getGender().equals("ALL")) {
            if (!leaveType.getGender().equals(employee.getGender())) {
                throw new RuntimeException("This leave type is only for this : "+ leaveType.getGender());
            }
        }

        // count checking..
        int leaveCount = leaveRepository.getLeaveCountByUserId(leaveRequest.getAppliedBy(), leaveType.getId());

        if(leaveType.getCount() == leaveCount) {
            logger.debug("No leaves left for you...");
            throw new RuntimeException("You have no leaves left of : " + leaveType.getName());
        }

        if((leaveCount + requestLeaveCount) > leaveType.getCount()) {
            throw new RuntimeException("You requested for :"+ requestLeaveCount + " which exceeds leaves left for you");
        }

        //set leave id here
        leaveRequest.setLeaveId(leaveType.getId());
        return leaveRepository.create(leaveRequest);
    }

    @Override
    public List<Leave> getMyLeavesByUserId(int userId) throws LoginNotFoundException {
        logger.debug("Getting my leaves by user Id: {}", userId);
        return leaveRepository.getMyLeavesByUserId(userId);
    }

    @Override
    public List<Leave> getEmployeeLeavesByManagerId(int userId) throws EmployeeNotFoundException, LoginNotFoundException {
        logger.debug("Getting Employee Leaves By Manager Id:{}", userId);
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