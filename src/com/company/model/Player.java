package com.company.model;

public class Player {
    private Integer idPlayer;
    private String namePlayer;
    private Integer age;
    private Integer idFootballClub;

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

    public Integer getIdFootballClub() {
        return idFootballClub;
    }

    public void setIdFootballClub(Integer idFootballClub) {
        this.idFootballClub = idFootballClub;
    }

    @Override
    public String toString() {
        return String.format("Players: id_p=%d,name_p=%s, age=%d", idPlayer, namePlayer, age);
    }
}
