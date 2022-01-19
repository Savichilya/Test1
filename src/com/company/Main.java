package com.company;

import com.company.model.Foot_clubs;
import com.company.model.Players;
import com.company.repository.Foot_clubsRepository;
import com.company.repository.PlayersRepository;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Players players = PlayersRepository.getInstance().getById(3);
        System.out.println(players);

        Foot_clubs foot_clubs = Foot_clubsRepository.getInstance().getById(2);
        System.out.println(foot_clubs);
    }
}