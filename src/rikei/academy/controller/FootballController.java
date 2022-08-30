package rikei.academy.controller;

import rikei.academy.model.manU.FootballPlayer;
import rikei.academy.service.cauthu.FootballServiceIMPL;
import rikei.academy.service.cauthu.IFootballService;

import java.util.List;

public class FootballController {
    IFootballService footballService = new FootballServiceIMPL();

    public List<FootballPlayer> showListFootball(){
        return footballService.findAll();
    }
    public void CreateFootball( FootballPlayer footballPlayer){
        footballService.save(footballPlayer);
        showListFootball();
    }
    public void deleteFootball(int id){
        footballService.deleteById(id);
        showListFootball();
    }
    public FootballPlayer detailFootball(int id){
        return footballService.findById(id);
    }
    public void EditFootball(int id,FootballPlayer footballPlayer){
        FootballPlayer newPlayer = footballService.findById(id);
        newPlayer.setName(footballPlayer.getName());
        newPlayer.setBirthDay(footballPlayer.getBirthDay());
        newPlayer.setNumber(footballPlayer.getNumber());
        newPlayer.setCountry(footballPlayer.getCountry());
    }
}
