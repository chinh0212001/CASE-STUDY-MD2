package rikei.academy.model.manU;

import java.io.Serializable;

public class FootballPlayer implements Serializable {
    private int id;
    private String name; //tên
    private int birthDay;//năm sinh
    private int number; //số áo
    private String country; // quốc gia

    public FootballPlayer(String newName, String newBirthDay, String newNUmber, String newCountry) {
        this.name = newName;
        this.birthDay =Integer.parseInt(newBirthDay) ;
        this.number =Integer.parseInt(newNUmber) ;
        this.country = newCountry;
    }

    public FootballPlayer(int id, String name, int birthDay, int number, String country) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.number = number;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "FootballPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", number=" + number +
                ", country='" + country + '\'' +
                '}';
    }

}
