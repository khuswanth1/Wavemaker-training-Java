package com.wm.Leavemanagement.Impl;

import com.wm.Leavemanagement.Employee.login;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import java.sql.SQLException;
public interface LoginRepository {
    login getByUserName(String userName) throws LoginNotFoundException, SQLException;
    login create(login login);
}
