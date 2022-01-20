package com.company.repository;

import com.company.model.Foot_clubs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Foot_clubsRepository {

    private static Foot_clubsRepository instance;

    private Foot_clubsRepository() {}

    public static Foot_clubsRepository getInstance() {
        if (instance == null) {
            instance = new Foot_clubsRepository();
        }
        return instance;
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
                foot_clubs.setPlayers(PlayersRepository.getInstance().getById(resultSet.getInt("id_fc")));
            }
            return foot_clubs;
        }
    }
}
