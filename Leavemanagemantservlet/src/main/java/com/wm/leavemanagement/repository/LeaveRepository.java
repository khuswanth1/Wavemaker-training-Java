package com.wm.leavemanagement.repository;

import com.wm.leavemanagement.pojo.Leave;
import com.wm.leavemanagement.pojo.LeaveEmp;
import com.wm.leavemanagement.pojo.LeaveTracker;
import com.wm.leavemanagement.exception.employeeNotFoundException;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.pojo.LeaveRequest;

import java.util.List;

public interface LeaveRepository {
    LeaveEmp create(LeaveRequest leaveRequest);

    List<Leave> getEmployeeLeavesByManagerId(int userId) throws employeeNotFoundException, loginNotFoundException;

    List<Leave> getMyLeavesByUserId(int userId) throws loginNotFoundException;

    void rejectLeave(int leaveId);

    void approveLeave(int leaveId);

    int getLeaveCountByUserId(int userId, int leaveId);

    List<LeaveTracker> getLeaveTrackerByUserId(int userId);
}