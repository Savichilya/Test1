package com.company.model;

public class Players {
    private Integer id_p;
    private String name_p;
    private Integer age;

    public Integer getId_p(){
        return id_p;
    }

    public void setId_p(Integer id_p) {
        this.id_p = id_p;
    }

    public String getName_p() {
        return name_p;
    }

    public void setName_p(String name_p) {
        this.name_p = name_p;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Players: id_p=%d,name_p=%s, age=%d", id_p, name_p, age);
    }
}
