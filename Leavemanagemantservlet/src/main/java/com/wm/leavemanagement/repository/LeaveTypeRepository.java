package com.wm.leavemanagement.repository;


import com.wm.leavemanagement.pojo.LeaveType;
import com.wm.leavemanagement.constants.LeaveName;
import com.wm.leavemanagement.exception.leaveTypeNotFoundException;

public interface LeaveTypeRepository {
    LeaveType getById(int id) throws leaveTypeNotFoundException;

    LeaveType getByName(LeaveName leaveName) throws leaveTypeNotFoundException;
}