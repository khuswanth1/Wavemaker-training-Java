package com.wm.leavemanagement.repository;

import com.wm.leavemanagement.pojo.Login;
import com.wm.leavemanagement.exception.loginNotFoundException;

import java.sql.SQLException;

public interface LoginRepository {
    Login getByUserName(String userName) throws loginNotFoundException, SQLException;

    Login create(Login login);
}
