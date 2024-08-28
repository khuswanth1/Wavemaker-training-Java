package com.db.Service.Emp;

import com.db.com.pack.userAccess; // Ensure this is the correct class for userAccess
import com.db.Repository.userAccessRepository;
import com.db.util.DatabaseConnects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAccessRepositoryEmp extends userAccessRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserAccessRepositoryEmp.class);

    @Override
    public boolean addUserLogin(userAccess userAccess) {
        String query = "INSERT INTO USERS (USER, PASSWORD) VALUES (?, ?)";

        try (Connection connection = DatabaseConnects.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Ensure that getUser() and getPassword() methods exist in userAccess
            preparedStatement.setString(1, userAccess.getUser());
            preparedStatement.setString(2, userAccess.getPassword());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.error("Error adding user login", e);
        }
        return false;
    }

    @Override
    public boolean addUserLogin(userAccessRepository userAccess) {
        return false;
    }
}
