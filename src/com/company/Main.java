package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Base.getInstance().addToTable("Savitski", 28);
        Base.getInstance().updateTable("Putsilooo", 55, 1);
    }
}