package com.wm.Leavemanagement.util;

import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);
    private final static  String URL = "jdbc:mysql://127.0.0.1:3306/LEAVE_MANAGMENT_APP";
    private final static String USERNAME = "root";
    private final static String PASSWORD =  "2205";

    private static volatile Connection connection;

    private DatabaseConnector() {}  //to ensure that no other class can't create the instance of this class by using the new keyword;
    public static Connection connect() throws SQLException {
        try {
            if(connection == null || !connection.isClosed()) {
                synchronized (DatabaseConnector.class) {
                    if(connection == null || !connection.isClosed()) {
                        DriverManager.registerDriver(new Driver());
                        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    }
                }
            }
        } catch (SQLException e) {
            logger.debug(e.toString());
        }
        return connection;
    }
}