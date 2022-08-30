package rikei.academy.service.coach;

import rikei.academy.config.Config;
import rikei.academy.model.manU.Coach;

import java.util.ArrayList;
import java.util.List;

public class CoachServiceIMPL implements ICoachService{
    public static String PATH_COACH = "src/rikei/academy/data/coach.txt";
    static Config<List<Coach>> config = new Config<>();
    static List<Coach> coachList = config.read(PATH_COACH);
    static {
        if (coachList == null){
            coachList = new ArrayList<>();
        }
    }
    @Override
    public List<Coach> findAll() {
        config.write(PATH_COACH,coachList);
        return coachList;
    }

    @Override
    public void save(Coach coach) {
        coachList.add(coach);
        config.write(PATH_COACH,coachList);

    }

    @Override
    public Coach findById(int id) {
        for (int i = 0; i < coachList.size(); i++) {
            if (id == coachList.get(i).getId()){
                return coachList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < coachList.size(); i++) {
            if (id == coachList.get(i).getId()){
                coachList.remove(i);
            }
        }

    }

}
