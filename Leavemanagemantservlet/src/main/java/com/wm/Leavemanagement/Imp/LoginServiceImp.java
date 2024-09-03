package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.login;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;

import com.wm.Leavemanagement.Impl.LoginRepository;
import com.wm.Leavemanagement.Impl.LoginRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class LoginServiceImp implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);
    private final LoginRepository loginRepository = LoginRepositoryImpl.getInstance();

    @Override
    public login getByUserName(String userName) throws LoginNotFoundException, SQLException {
        logger.debug("Getting login details with userName: {}", userName);
        return loginRepository.getByUserName(userName);
    }

    @Override
    public login create(login login) {
        logger.debug("Adding into Login with details : {}", login);
        return loginRepository.create(login);
    }
}