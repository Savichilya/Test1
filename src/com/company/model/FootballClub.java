package com.company.model;

public class FootballClub {
    private Integer idFootballClub;
    private String nameFootballClub;
    private Integer yearBirth;

    public Integer getIdFootballClub() {
        return idFootballClub;
    }

    public void setIdFootballClub(Integer idFootballClub) {
        this.idFootballClub = idFootballClub;
    }

    public String getNameFootballClub() {
        return nameFootballClub;
    }

    public void setNameFootballClub(String nameFootballClub) {
        this.nameFootballClub = nameFootballClub;
    }

    public Integer getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(Integer yearBirth) {
        this.yearBirth = yearBirth;
    }

    @Override
    public String toString() {
        return String.format("Foot_clubs: id_fc=%d,name_fc=%s, year_birth=%d", idFootballClub, nameFootballClub, yearBirth);
    }
}
