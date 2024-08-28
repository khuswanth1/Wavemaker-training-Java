package com.Wm.Todo.Service.Imp;


import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.pack.usernameandpassword;
import com.Wm.Todo.Repository.UserPasswordRepository;
import com.Wm.Todo.Repository.Imp.UserPasswordRepositoryImp;
import com.Wm.Todo.Service.UserService;

import java.sql.SQLException;

public class UserServiceImp implements UserService {
    private static UserPasswordRepository userPasswordRepository = null;

    public UserServiceImp() throws SQLException {
        userPasswordRepository = new UserPasswordRepositoryImp();
    }

    @Override
    public int authenticateUser(usernameandpassword usernameandPassword) throws ServerUnavilableException {
        return userPasswordRepository.authenticateUser(usernameandPassword);
    }

}