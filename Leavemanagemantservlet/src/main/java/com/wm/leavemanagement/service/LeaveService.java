package com.wm.leavemanagement.service;

import com.wm.leavemanagement.pojo.Leave;
import com.wm.leavemanagement.pojo.LeaveEmp;
import com.wm.leavemanagement.pojo.LeaveTracker;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.leaveTypeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.pojo.LeaveRequest;

import java.util.List;

public interface LeaveService {

    LeaveEmp create(LeaveRequest leaveRequest) throws leaveTypeNotFoundException, loginNotFoundException;

    List<Leave> getMyLeavesByUserId(int userId) throws loginNotFoundException;

    List<Leave> getEmployeeLeavesByManagerId(int userId) throws employeeNotFoundException, loginNotFoundException;

    void approveLeave(int leaveId);

    void rejectLeave(int leaveId);

    List<LeaveTracker> getLeavesTrackerByUserId(int userId);

}