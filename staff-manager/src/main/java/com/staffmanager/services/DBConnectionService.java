package com.staffmanager.services;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionService {
    private static Connection connection = null;

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        try {
            if (connection == null) {
                connection = new Connection(url, user, password);
            }
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }

}