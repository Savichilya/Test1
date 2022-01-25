package com.company.repository;

import com.company.model.FootballClub;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class FootballClubRepository {

    private static FootballClubRepository instance;

    private FootballClubRepository() {
    }

    public static FootballClubRepository getInstance() {
        if (instance == null) {
            instance = new FootballClubRepository();
        }
        return instance;
    }

    public FootballClub addFootballClub(FootballClub footClub) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO foot_clubs(name_fc, year_birth) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, footClub.getNameFootballClub());
            prepareStatement.setInt(2, footClub.getYearBirth());
            prepareStatement.execute();

            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    footClub.setIdFootballClub(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return footClub;
        }
    }

    public Optional<FootballClub> getById(int id) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT id_fc, name_fc, year_birth from foot_clubs where id_fc=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                FootballClub footballClub = new FootballClub();
                footballClub.setIdFootballClub(resultSet.getInt("id_fc"));
                footballClub.setNameFootballClub(resultSet.getString("name_fc"));
                footballClub.setYearBirth(resultSet.getInt("year_birth"));
return Optional.of(footballClub);
            }
            return Optional.empty();
        }
    }
}
