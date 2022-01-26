package com.company.model;

public class Player extends FootballClub {
    private Integer idPlayer;
    private String namePlayer;
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
        return String.format("Players: id_p=%d,name_p=%s, age=%d, id_fc=%d, name_fc=%s", idPlayer, namePlayer, age, footballClub.getIdFootballClub(), footballClub.getNameFootballClub());
    }


}
