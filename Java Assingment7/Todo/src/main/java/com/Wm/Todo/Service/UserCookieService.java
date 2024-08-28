package com.Wm.Todo.Service;

import com.Wm.Todo.Exception.ServerUnavilableException;

public interface UserCookieService {
    public boolean addCookie(String cookieValue, int userId) throws ServerUnavilableException;

    public int getUserIdByCookieValue(String cookieValue) throws ServerUnavilableException;
}