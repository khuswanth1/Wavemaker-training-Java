package com.Wm.Todo.Repository;


import com.Wm.Todo.Exception.ServerUnavilableException;

public interface UserCookieRepository {
    public boolean addCookie(String cookieValue, int userId) throws ServerUnavilableException;
    public int getUserIdByCookieValue(String cookieValue) throws ServerUnavilableException;
}