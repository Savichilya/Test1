package com.company.model;

import com.company.annotation.Column;

public class Player extends FootballClub {

    @Column("id_p")
    private Integer idPlayer;

    @Column("name_p")
    private String namePlayer;

    @Column("age")
    private Integer age;
    private FootballClub footballClub;

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public FootballClub getFootballClub() {
        return footballClub;
    }

    public void setFootballClub(FootballClub footballClub) {
        this.footballClub = footballClub;
    }

    @Override
    public String toString() {
        return String.format("Players: id_p=%d,name_p=%s, age=%d, foot_clubs.id_fc=%d, foot_clubs.name_fc=%s, " +
                "foot_clubs.year_birth=%d", idPlayer, namePlayer, age, footballClub.getIdFootballClub(), footballClub.getNameFootballClub(), footballClub.getYearBirth());
    }


}
