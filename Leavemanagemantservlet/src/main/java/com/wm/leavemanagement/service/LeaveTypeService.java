package com.wm.leavemanagement.service;

import com.wm.leavemanagement.pojo.LeaveType;
import com.wm.leavemanagement.constants.LeaveName;
import com.wm.leavemanagement.exception.leaveTypeNotFoundException;

public interface LeaveTypeService {
    LeaveType getById(int id) throws leaveTypeNotFoundException;

    LeaveType getByName(LeaveName leaveName) throws leaveTypeNotFoundException;
}