package com.wm.leavemanagement.service;

import com.wm.leavemanagement.pojo.Login;
import com.wm.leavemanagement.exception.loginNotFoundException;

import java.sql.SQLException;

public interface LoginService {
    Login getByUserName(String userName) throws loginNotFoundException, SQLException;

    Login create(Login login);
}