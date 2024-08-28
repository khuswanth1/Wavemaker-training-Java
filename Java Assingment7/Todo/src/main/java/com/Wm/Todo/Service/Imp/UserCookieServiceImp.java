package com.Wm.Todo.Service.Imp;


import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.Repository.UserCookieRepository;
import com.Wm.Todo.Repository.Imp.UserCookieRepositoryImp;
import com.Wm.Todo.Service.UserCookieService;

import java.sql.SQLException;

public class UserCookieServiceImp implements UserCookieService {
    private static UserCookieRepository userCookieRepository;

    public UserCookieServiceImp() throws SQLException {
        userCookieRepository = new UserCookieRepositoryImp();
    }

    @Override
    public boolean addCookie(String cookieValue, int userId) throws ServerUnavilableException {
        return userCookieRepository.addCookie(cookieValue, userId);
    }

    @Override
    public int getUserIdByCookieValue(String cookieValue) throws ServerUnavilableException {
        return userCookieRepository.getUserIdByCookieValue(cookieValue);
    }
}