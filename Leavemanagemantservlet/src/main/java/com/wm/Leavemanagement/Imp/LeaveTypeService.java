package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.LeaveType;
import com.wm.Leavemanagement.Enums.LeaveName;
import com.wm.Leavemanagement.Exception.LeaveTypeNotFoundException;

public interface LeaveTypeService {
    LeaveType getById(int id) throws LeaveTypeNotFoundException;
    LeaveType getByName(LeaveName leaveName) throws LeaveTypeNotFoundException;
}