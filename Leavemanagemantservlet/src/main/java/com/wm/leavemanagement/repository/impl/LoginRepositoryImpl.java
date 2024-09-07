package com.wm.leavemanagement.repository.impl;

import com.wm.leavemanagement.repository.LoginRepository;
import com.wm.leavemanagement.pojo.Login;
import com.wm.leavemanagement.exception.loginNotFoundException;
import com.wm.leavemanagement.util.DatabaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImpl implements LoginRepository {
    private static final Logger logger = LoggerFactory.getLogger(LoginRepositoryImpl.class);
    private static final String CREATE_LOGIN = "INSERT INTO LOGIN(USER_NAME, PASSWORD) VALUES(?,?)";
    private static final String GET_LOGIN_BY_USER_NAME = "SELECT USER_NAME, PASSWORD FROM LOGIN WHERE USER_NAME = ?";
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
    public Login getByUserName(String userName) throws loginNotFoundException, SQLException {
        logger.debug("Getting Login Details by userName: {}", userName);
        Login login = null;
        try {
            PreparedStatement preparedStatement = DatabaseConnector.connect().prepareStatement(GET_LOGIN_BY_USER_NAME);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean flag = resultSet.next();
            if (flag) {
                login = new Login();
                login.setUserName(resultSet.getString("USER_NAME"));
                login.setPassword(resultSet.getString("PASSWORD"));
            } else {
                throw new loginNotFoundException("Invalid UserName: " + userName);
            }
        } catch (SQLException e) {
            logger.error("SQL exception occurred while getting login details for userName: {}", userName, e);
            throw new SQLException("Database error occurred");
        }
        return login;
    }

    @Override
    public Login create(Login login) {
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