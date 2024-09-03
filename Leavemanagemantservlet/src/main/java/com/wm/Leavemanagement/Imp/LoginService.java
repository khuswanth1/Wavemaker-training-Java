package com.wm.Leavemanagement.Imp;

import com.wm.Leavemanagement.Employee.login;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;

import java.sql.SQLException;

public interface LoginService {
    login getByUserName(String userName) throws LoginNotFoundException, SQLException;
    login create(login login);
}