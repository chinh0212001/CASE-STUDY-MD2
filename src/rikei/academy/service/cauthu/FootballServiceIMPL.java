package rikei.academy.service.cauthu;

import rikei.academy.config.Config;
import rikei.academy.model.manU.FootballPlayer;

import java.util.ArrayList;
import java.util.List;

public class FootballServiceIMPL implements IFootballService{
    public static String PATH_Football = "src/rikei/academy/data/football.txt";
    static Config<List<FootballPlayer>> config = new Config<>();
    static List<FootballPlayer> playerList = config.read(PATH_Football);
    static {
        if (playerList == null){
            playerList = new ArrayList<>();
        }
    }
    @Override
    public List<FootballPlayer> findAll() {
        config.write(PATH_Football,playerList);
        return playerList;
    }

    @Override
    public void save(FootballPlayer footballPlayer) {
        playerList.add(footballPlayer);
        config.write(PATH_Football,playerList);

    }

    @Override
    public FootballPlayer findById(int id) {
        for (int i = 0; i < playerList.size(); i++) {
            if (id == playerList.get(i).getId()){
                return playerList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < playerList.size(); i++) {
            if (id == playerList.get(i).getId()){
                playerList.remove(i);
            }
        }
    }
}
