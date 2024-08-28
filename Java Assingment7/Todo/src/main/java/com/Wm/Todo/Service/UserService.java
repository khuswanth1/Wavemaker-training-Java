package com.Wm.Todo.Service;

import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.pack.usernameandpassword;

public interface UserService {
    public int authenticateUser(usernameandpassword usernameAndPassword) throws ServerUnavilableException;
}

