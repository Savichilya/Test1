package com.company.repository;

import com.company.annotation.Column;
import com.company.model.FootballClub;
import com.company.model.Player;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public Optional<FootballClub> getById(int id) throws SQLException, IllegalAccessException {
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT id_fc, name_fc, year_birth from foot_clubs where id_fc=?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                FootballClub footballClub = new FootballClub();
                for (Field field : FootballClub.class.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class)) {
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        String columnName = column.value();
                        Object value = null;
                        if (field.getGenericType() == String.class) {
                            value = resultSet.getString(columnName);
                        } else if (field.getGenericType() == Integer.class) {
                            value = resultSet.getInt(columnName);
                        }
                        field.set(footballClub, value);
                    }
                }
                return Optional.of(footballClub);
            }
            return Optional.empty();
        }
    }

    public List<FootballClub> returnNumbersFootballClubsByID(int id) throws SQLException, IllegalAccessException {
        List<FootballClub> footballClubList = new ArrayList<>();
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT f.name_fc," +
                " COUNT(p.id_fc) AS numberOfPlayers FROM players p JOIN foot_clubs f ON p.id_fc=f.id_fc GROUP BY f.name_fc" +
                " HAVING COUNT(p.id_fc)>?")) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                FootballClub footballClub = new FootballClub();
                for (Field field : FootballClub.class.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class)) {
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        String columnName = column.value();
                        Object value = null;
                        if (field.getGenericType() == String.class) {
                            value = resultSet.getString(columnName);
                        }
                        field.set(footballClub, value);
                    }
                }
                footballClubList.add(footballClub);
            }
            return footballClubList;
        }
    }

    public List<FootballClub> returnFootballClubsByYear(int a) throws SQLException, IllegalAccessException {
        List<FootballClub> footballClubsArrayList = new ArrayList<>();
        try (PreparedStatement prepareStatement = ConnectionHolder.getConnection().prepareStatement("SELECT name_fc FROM foot_clubs WHERE year_birth LIKE ?")) {
            prepareStatement.setInt(1, a);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                FootballClub footballClub = new FootballClub();
                for (Field field : FootballClub.class.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class)) {
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        String columnName = column.value();
                        Object value = null;
                        if (field.getGenericType() == String.class) {
                            value = resultSet.getString(columnName);
                            field.set(footballClub, value);
                        }
                    }
                }
                footballClubsArrayList.add(footballClub);
            }
            return footballClubsArrayList;
        }
    }
}
