package com.wm.Leavemanagement.Impl;


import com.wm.Leavemanagement.Employee.LeaveType;
import com.wm.Leavemanagement.Enums.LeaveName;
import com.wm.Leavemanagement.Exception.LeaveTypeNotFoundException;

public interface LeaveTypeRepository {
    LeaveType getById(int id) throws LeaveTypeNotFoundException;
    LeaveType getByName(LeaveName leaveName) throws LeaveTypeNotFoundException;
}