package com.company;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost/football";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery("SELECT * FROM foot_clubs")) {
                    logResalt(result);
                }
            } catch (Exception e) {
                String message = e.getMessage();
                logError(message);
            }
        }
    }

    private static void logResalt(ResultSet resaltset) throws SQLException {
        while (resaltset.next()) {
            int a = 1;
            StringBuilder resaltString = new StringBuilder("|");
            while (a <= resaltset.getMetaData().getColumnCount()) {
                resaltString.append(resaltset.getString(a));
                resaltString.append("|");
                a++;
            }
            System.out.println(resaltString);
        }
    }
    private static void logError(String message) {
        System.out.printf("ERROR :: %s%n", message);
    }




}