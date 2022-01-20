package com.company.repository;

import com.company.model.Foot_clubs;
import com.company.model.Players;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Foot_clubsRepository {

    private static Foot_clubsRepository instance;

    private Foot_clubsRepository() {
    }

    public static Foot_clubsRepository getInstance() {
        if (instance == null) {
            instance = new Foot_clubsRepository();
        }
        return instance;
    }

    public Foot_clubs addFootClubs(Foot_clubs foot_clubs) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("INSERT INTO foot_clubs(name_fc, year_birth) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, foot_clubs.getName_fc());
            prepareStatement.setInt(2, foot_clubs.getYear_birth());
            prepareStatement.execute();

            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    foot_clubs.setId_fc(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return foot_clubs;
        }
    }

    public Foot_clubs getById(int id) throws SQLException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT id_fc, name_fc, year_birth from foot_clubs where id_fc=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            Foot_clubs foot_clubs = null;
            if (resultSet.next()) {
                foot_clubs = new Foot_clubs();
                foot_clubs.setId_fc(resultSet.getInt("id_fc"));
                foot_clubs.setName_fc(resultSet.getString("name_fc"));
                foot_clubs.setYear_birth(resultSet.getInt("year_birth"));

            }
            return foot_clubs;
        }
    }
}
