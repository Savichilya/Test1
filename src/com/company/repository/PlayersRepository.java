package com.company.repository;

import com.company.model.Players;

import java.sql.*;

public class PlayersRepository {

    private static PlayersRepository instance;

    private PlayersRepository() {
    }

    public static PlayersRepository getInstance() {
        if (instance == null) {
            instance = new PlayersRepository();
        }
        return instance;
    }

    public Players addPlayers(Players players) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO players(name_p, age) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS )) {
            prepareStatement.setString(1, players.getName_p());
            prepareStatement.setInt(2, players.getAge());
            prepareStatement.execute();

            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    players.setId_p(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return players;
        }
    }

    public Players getById(int id) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT id_p, name_p, age from players where id_p=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            Players players = null;
            if (resultSet.next()) {
                players = new Players();
                players.setId_p(resultSet.getInt("id_p"));
                players.setName_p(resultSet.getString("name_p"));
                players.setAge(resultSet.getInt("age"));
            }
            return players;
        }
    }

    public void updatePlayers(String name_p, int age, int Id_p) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("UPDATE players SET name_p = ?, age=? WHERE id_p=?")) {
            prepareStatement.setString(1, name_p);
            prepareStatement.setInt(2, age);
            prepareStatement.setInt(3, Id_p);
            prepareStatement.execute();
        }
    }


}

