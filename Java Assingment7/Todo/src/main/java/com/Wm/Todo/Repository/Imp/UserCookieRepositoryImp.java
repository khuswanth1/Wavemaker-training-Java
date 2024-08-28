package com.Wm.Todo.Repository.Imp;


import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.Repository.UserCookieRepository;
import com.Wm.Todo.util.DatabaseConnector;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCookieRepositoryImp implements UserCookieRepository {
    private static Connection connection;
    private static final String GET_USER_ID = "SELECT USER_ID FROM USER_COOKIES WHERE COOKIE_VALUE = ?";

    public UserCookieRepositoryImp() throws SQLException {
        connection = DatabaseConnector.getConnectionInstance();
    }

    @Override
    public boolean addCookie(String cookieValue, int userId) throws ServerUnavilableException {
        String insertCookieSQL = "INSERT INTO COOKIES (COOKIE_NAME, COOKIE_VALUE, USER_ID) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCookieSQL);
            String cookieName = "my_auth_cookie"; //default cookie name
            preparedStatement.setString(1, cookieName);
            preparedStatement.setString(2, cookieValue);
            preparedStatement.setInt(3, userId);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new ServerUnavilableException("Failed to add cookie due to a database error.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public int getUserIdByCookieValue(String cookieValue) throws ServerUnavilableException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ID);
            preparedStatement.setString(1, cookieValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("USER_ID");
                }
            }
        } catch (SQLException e) {
            throw new ServerUnavilableException("Failed to retrieve user ID by cookie value.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return -1;
    }

}