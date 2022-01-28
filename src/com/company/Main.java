package com.company;

import com.company.model.FootballClub;
import com.company.model.Player;
import com.company.repository.FootballClubRepository;
import com.company.repository.PlayerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("Клубы у которых больше заявленного вами количества игроков = " + FootballClubRepository.getInstance().returnNumbersFootballClubsByID(5));

        //System.out.println("Сумма клубов = " + PlayerRepository.getInstance().returnNumberOfPlayerInFootballClub(3));

        /*
        FootballClub footballClub1 = new FootballClub();
        footballClub1.setIdFootballClub(1);
        System.out.println(PlayerRepository.getInstance().returnPlayersOfFootballClub(footballClub1));
*/


            /*
            try {
            ConnectionHolder.getConnection().setAutoCommit(false);
            Player player=new Player();
            player.setNamePlayer("Savich");
            player.setAge(31);

            PlayerRepository.getInstance().addPlayer(player);

            FootballClub footballClub=new FootballClub();
            footballClub.setNameFootballClub("Manchester Utd");
            footballClub.setYearBirth(1911);

            FootballClubRepository.getInstance().addFootballClub(footballClub);
            ConnectionHolder.getConnection().commit();
        }catch (Exception a){
            ConnectionHolder.getConnection().rollback();
        }finally {
            ConnectionHolder.getConnection().setAutoCommit(true);;
        }
*/

       /*
        Player player = PlayerRepository.getInstance().getById(3);
        System.out.println(player);

        FootballClub footballClub = FootballClubRepository.getInstance().getById(2);
        System.out.println(footballClub);
        */

        // System.out.println(PlayerRepository.getInstance().getPlayerByIdWithJoin(3).get());
    }
}