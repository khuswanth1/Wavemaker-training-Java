package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.Leave;
import com.wm.Leavemanagement.Employee.LeaveTracker;
import com.wm.Leavemanagement.Employee.LeaveEmp;
import com.wm.Leavemanagement.Exception.EmployeeNotFoundException;
import com.wm.Leavemanagement.Exception.LeaveTypeNotFoundException;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import com.wm.Leavemanagement.model.LeaveRequest;

import java.util.List;

public interface LeaveService {

    LeaveEmp create(LeaveRequest leaveRequest) throws LeaveTypeNotFoundException, LoginNotFoundException;
    List<Leave> getMyLeavesByUserId(int userId) throws LoginNotFoundException;
    List<Leave> getEmployeeLeavesByManagerId(int userId) throws EmployeeNotFoundException, LoginNotFoundException;
    void approveLeave(int leaveId);
    void rejectLeave(int leaveId);
    List<LeaveTracker> getLeavesTrackerByUserId(int userId);

}