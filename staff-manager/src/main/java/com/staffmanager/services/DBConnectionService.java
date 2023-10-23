package com.staffmanager.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionService {
    private static Connection connection = null;

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url, user, password);
            }
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }

}