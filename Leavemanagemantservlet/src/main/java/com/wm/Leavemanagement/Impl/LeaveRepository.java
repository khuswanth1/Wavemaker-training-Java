package com.wm.Leavemanagement.Impl;

import com.wm.Leavemanagement.Employee.LeaveEmp;
import com.wm.Leavemanagement.Employee.Leave;
import com.wm.Leavemanagement.Employee.LeaveTracker;
import com.wm.Leavemanagement.Exception.EmployeeNotFoundException;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import com.wm.Leavemanagement.model.LeaveRequest;

import java.util.List;

public interface LeaveRepository {
    LeaveEmp create(LeaveRequest leaveRequest);

    List<Leave> getEmployeeLeavesByManagerId(int userId) throws EmployeeNotFoundException, LoginNotFoundException;

    List<Leave> getMyLeavesByUserId(int userId) throws LoginNotFoundException;

    void rejectLeave(int leaveId);

    void approveLeave(int leaveId);

    int getLeaveCountByUserId(int userId, int leaveId);

    List<LeaveTracker> getLeaveTrackerByUserId(int userId);
}