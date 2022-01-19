package com.company;

import java.sql.*;

public class Base implements AutoCloseable {
    private static final String URL = "jdbc:mysql://localhost/football";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    private static Base instans = new Base();

    private Base() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Base getInstance(){
        if (instans == null){
            instans = new Base();
        }
        return instans;
    }

    public void addToTable(String name_p, int age) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO players(name_p, age) VALUES(?,?)")) {
            prepareStatement.setString(1, name_p);
            prepareStatement.setInt(2, age);
            prepareStatement.execute();
        }
    }

    public void updateTable(String name_p, int age, int Id_p) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("UPDATE players SET name_p = ?, age=? WHERE id_p=?")) {
            prepareStatement.setString(1, name_p);
            prepareStatement.setInt(2, age);
            prepareStatement.setInt(3, Id_p);
            prepareStatement.execute();
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

}

