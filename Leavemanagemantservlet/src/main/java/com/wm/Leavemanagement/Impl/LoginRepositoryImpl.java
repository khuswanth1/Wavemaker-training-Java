package com.wm.Leavemanagement.Impl;

import com.wm.Leavemanagement.Employee.login;
import com.wm.Leavemanagement.Exception.LoginNotFoundException;
import com.wm.Leavemanagement.util.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginRepositoryImpl implements LoginRepository {
    private static final Logger logger = LoggerFactory.getLogger(LoginRepositoryImpl.class);
    private static final String CREATE_LOGIN = "INSERT INTO LOGIN(USER_NAME, PASSWORD) VALUES(?,?)";
    private static final String GET_LOGIN_BY_USER_NAME = "SELECT * FROM LOGIN WHERE USER_NAME = ?";
    private static LoginRepositoryImpl instance;
    private LoginRepositoryImpl() {
    }
    public static synchronized LoginRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new LoginRepositoryImpl();
        }
        return instance;
    }
    @Override
    public login getByUserName(String userName) throws LoginNotFoundException, SQLException {
        logger.debug("Getting Login Details by userName: {}", userName);
        login login = null;
        try {
            PreparedStatement preparedStatement = DatabaseConnector.connect().prepareStatement(GET_LOGIN_BY_USER_NAME);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                login = new login();
                login.setUserName(resultSet.getString(1));
                login.setPassword(resultSet.getString(2));
            } else {
                throw new LoginNotFoundException("Invalid UserName: " + userName);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception occurred while getting login details for userName: {}", userName, e);
            throw new SQLException("Database error occurred");
        }
        return login;
    }
    @Override
    public login create(login login) {
        logger.debug("Adding Login Details into database with details {}", login);
        try {
            PreparedStatement preparedStatement = DatabaseConnector.connect().prepareStatement(CREATE_LOGIN);
            preparedStatement.setString(1, login.getUserName());
            preparedStatement.setString(2, login.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return login;
    }
}