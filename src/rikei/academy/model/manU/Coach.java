package rikei.academy.model.manU;

import java.io.Serializable;

public class Coach implements Serializable {
    private int id;
    private String name;
    private int birthDay;

    public Coach(String newName, String newBrithDay) {
        this.name = newName;
        this.birthDay = Integer.parseInt(newBrithDay);
    }

    public Coach(int id, String name, int birthDay) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
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

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
