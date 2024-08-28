package com.Wm.Todo.Repository;

import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.pack.usernameandpassword ;

public interface UserPasswordRepository {
    public int authenticateUser(usernameandpassword  usernameAndPassword) throws ServerUnavilableException;
}