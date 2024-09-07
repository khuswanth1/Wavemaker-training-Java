package com.wm.leavemanagement.service.impl;

import com.wm.leavemanagement.service.LoginService;
import com.wm.leavemanagement.pojo.Login;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.repository.LoginRepository;
import com.wm.leavemanagement.repository.impl.LoginRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class LoginServiceImp implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);
    private final LoginRepository loginRepository = LoginRepositoryImpl.getInstance();

    @Override
    public Login getByUserName(String userName) throws loginNotFoundException, SQLException {
        logger.debug("Getting login details with userName: {}", userName);
        return loginRepository.getByUserName(userName);
    }

    @Override
    public Login create(Login login) {
        logger.debug("Adding into Login with details : {}", login);
        return loginRepository.create(login);
    }
}