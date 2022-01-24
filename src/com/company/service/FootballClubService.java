package com.company.service;

import com.company.model.FootballClub;
import com.company.model.Player;
import com.company.repository.FootballClubRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

public class FootballClubService {

    private static FootballClubService instance;

    private FootballClubService() {
    }

    public static FootballClubService getInstance() {
        if (instance == null) {
            instance = new FootballClubService();
        }
        return instance;
    }

    public FootballClub saveFootballClub(FootballClub footballClub) throws SQLException {
        return FootballClubRepository.getInstance().addFootballClub(footballClub);
    }
}
