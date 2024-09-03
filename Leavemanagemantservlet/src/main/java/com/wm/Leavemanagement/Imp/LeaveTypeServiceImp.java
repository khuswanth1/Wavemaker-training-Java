package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.LeaveType;
import com.wm.Leavemanagement.Enums.LeaveName;
import com.wm.Leavemanagement.Exception.LeaveTypeNotFoundException;

import com.wm.Leavemanagement.Impl.LeaveTypeRepository;
import com.wm.Leavemanagement.Impl.LeaveTypeRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaveTypeServiceImp implements LeaveTypeService {
    LeaveTypeRepository repository = LeaveTypeRepositoryImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(LeaveTypeServiceImp.class);

    @Override
    public LeaveType getById(int id) throws LeaveTypeNotFoundException {
        logger.debug("Getting leave Type by id: {}", id);
        return repository.getById(id);
    }

    @Override
    public LeaveType getByName(LeaveName leaveName) throws LeaveTypeNotFoundException {
        logger.debug("Getting leave type by name: {}", leaveName);
        return repository.getByName(leaveName);
    }
}