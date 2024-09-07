package com.wm.leavemanagement.service.impl;

import com.wm.leavemanagement.service.LeaveTypeService;
import com.wm.leavemanagement.pojo.LeaveType;
import com.wm.leavemanagement.constants.LeaveName;
import com.wm.leavemanagement.exception.leaveTypeNotFoundException;
import com.wm.leavemanagement.repository.LeaveTypeRepository;
import com.wm.leavemanagement.repository.impl.LeaveTypeRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaveTypeServiceImp implements LeaveTypeService {
    LeaveTypeRepository repository = LeaveTypeRepositoryImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(LeaveTypeServiceImp.class);

    @Override
    public LeaveType getById(int id) throws leaveTypeNotFoundException {
        logger.debug("Getting leave Type by id: {}", id);
        return repository.getById(id);
    }

    @Override
    public LeaveType getByName(LeaveName leaveName) throws leaveTypeNotFoundException {
        logger.debug("Getting leave type by name: {}", leaveName);
        return repository.getByName(leaveName);
    }
}