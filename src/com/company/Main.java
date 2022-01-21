package com.company;

import com.company.model.FootballClub;
import com.company.model.Player;
import com.company.repository.FootballClubRepository;
import com.company.repository.PlayerRepository;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Player player = PlayerRepository.getInstance().getById(3);
        System.out.println(player);

        FootballClub footballClub = FootballClubRepository.getInstance().getById(2);
        System.out.println(footballClub);
    }
}