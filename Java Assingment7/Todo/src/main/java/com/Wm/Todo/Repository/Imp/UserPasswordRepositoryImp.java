package com.Wm.Todo.Repository.Imp;


import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.pack.usernameandpassword;
import com.Wm.Todo.Repository.UserPasswordRepository;
import com.Wm.Todo.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPasswordRepositoryImp implements UserPasswordRepository {
    String SEARCH_USER = "SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";
    private static Connection connection;

    public UserPasswordRepositoryImp() throws SQLException {
        connection = DatabaseConnector.getConnectionInstance();
    }

    @Override
    public int authenticateUser(usernameandpassword usernameandpassword) throws ServerUnavilableException {
        String username = usernameandpassword.getUsername();
        String password = usernameandpassword.getPassword();
        int userId = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("USER_ID");
            }

        } catch (SQLException e) {
            throw new ServerUnavilableException("Unable to Authenticate the User", 500);
        }
        return userId;
    }

}