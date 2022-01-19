package com.company.service;

import com.company.model.Players;
import com.company.repository.PlayersRepository;

import java.sql.SQLException;

public class PlayersService {

    private static PlayersService instance;

    private PlayersService () {}

    public static PlayersService getInstance() {
        if (instance == null) {
            instance = new PlayersService();
        }
        return instance;
    }

    public Players savePlayers(Players  players) throws SQLException {
        return PlayersRepository.getInstance().addPlayers(players);
    }
}
