package com.company.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHolder implements AutoCloseable {

    public static final String URL = "jdbc:mysql://localhost/football";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private ConnectionHolder() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
