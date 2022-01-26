package com.company.repository;

import com.company.model.FootballClub;
import com.company.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO players(name_p, age, id_fc) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, player.getNamePlayer());
            prepareStatement.setInt(2, player.getAge());
            prepareStatement.setInt(3, player.getFootballClub() != null ? player.getFootballClub().getIdFootballClub() : 4);
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

    public Optional<Player> getById(int id) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT id_p, name_p, age, id_fc from players where id_p=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                Player player = new Player();
                player.setIdPlayer(resultSet.getInt("id_p"));
                player.setNamePlayer(resultSet.getString("name_p"));
                player.setAge(resultSet.getInt("age"));
                Optional<FootballClub> optionalFootballClub = FootballClubRepository.getInstance().getById(resultSet.getInt("id_fc"));
                if (optionalFootballClub.isPresent()) {
                    player.setFootballClub(optionalFootballClub.get());
                }
                return Optional.of(player);
            }
            return Optional.empty();
        }
    }

    public Optional<Player> getPlayerByIdWithJoin(int id) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT p.id_p, p.name_p, " +
                " p.age, f.id_fc, f.name_fc, f.year_birth from players p " +
                " JOIN foot_clubs f ON f.id_fc=p.id_fc WHERE p.id_p=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                Player player = new Player();
                player.setIdPlayer(resultSet.getInt("id_p"));
                player.setNamePlayer(resultSet.getString("name_p"));
                player.setAge(resultSet.getInt("age"));

                if (resultSet.getString("id_fc") != null) {
                    FootballClub footballClub = new FootballClub();
                    footballClub.setIdFootballClub(resultSet.getInt("id_fc"));
                    footballClub.setNameFootballClub(resultSet.getString("name_fc"));
                    footballClub.setYearBirth(resultSet.getInt("year_birth"));
                    player.setFootballClub(footballClub);
                }
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    public List<Player> returnPlayerOfFootballClub(FootballClub footballClub) throws SQLException {
        List <Player> players=new ArrayList<>();
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT p.id_p, " +
                "p.name_p, p.age, f.id_fc, f.name_fc from players p " +
                "JOIN foot_clubs f ON f.id_fc=p.id_fc WHERE f.id_fc=?")) {
            prepareStatement.setInt(1, footballClub.getIdFootballClub());
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                    Player player = new Player();
                    player.setIdPlayer(resultSet.getInt("id_p"));
                    player.setNamePlayer(resultSet.getString("name_p"));
                    player.setAge(resultSet.getInt("age"));

                    if (resultSet.getInt("id_fc") != 0) {
                        footballClub.setIdFootballClub(resultSet.getInt("id_fc"));
                        footballClub.setNameFootballClub(resultSet.getString("name_fc"));
                        player.setFootballClub(footballClub);
                    }
                    players.add(player);
                }
            }
            return players;
    }

    public int returnNumberOfPlayerInFootballClub(int id) throws SQLException {
        int number=0;
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT COUNT(*)" +
                " FROM Players WHERE id_fc=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

        }
        return number;
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

