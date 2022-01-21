package com.company.repository;

import com.company.model.Player;

import java.sql.*;

public class PlayerRepository {

    private static PlayerRepository instance;

    private PlayerRepository() {
    }

    public static PlayerRepository getInstance() {
        if (instance == null) {
            instance = new PlayerRepository();
        }
        return instance;
    }

    public Player addPlayer(Player player) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO players(name_p, age) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, player.getNamePlayer());
            prepareStatement.setInt(2, player.getAge());
            prepareStatement.execute();

            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    player.setIdPlayer(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return player;
        }
    }

    public Player getById(int id) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT id_p, name_p, age, id_fc from players where id_p=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            Player player = null;
            if (resultSet.next()) {
                player = new Player();
                player.setIdPlayer(resultSet.getInt("id_p"));
                player.setNamePlayer(resultSet.getString("name_p"));
                player.setAge(resultSet.getInt("age"));
                player.setIdFootballClub(resultSet.getInt("id_fc"));

            }
            return player;
        }
    }

    public void updatePlayer(String name_p, int age, int Id_p) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("UPDATE players SET name_p = ?, age=? WHERE id_p=?")) {
            prepareStatement.setString(1, name_p);
            prepareStatement.setInt(2, age);
            prepareStatement.setInt(3, Id_p);
            prepareStatement.execute();
        }
    }


}

