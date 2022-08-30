package rikei.academy.controller;

import rikei.academy.model.manU.Coach;
import rikei.academy.service.coach.CoachServiceIMPL;
import rikei.academy.service.coach.ICoachService;

import java.util.List;

public class CoachController {
    ICoachService coachService = new CoachServiceIMPL();
    public List<Coach> showListCoach(){
        return coachService.findAll();
    }
    public void CreateCoach( Coach coach){
        coachService.save(coach);
        showListCoach();
    }
    public void deleteCoach( int id){
        coachService.deleteById(id);
        showListCoach();
    }
    public Coach detailCoach(int id){
        return coachService.findById(id);
    }
    public void EditCoach(int id , Coach coach){
        Coach newCoach = coachService.findById(id);
        newCoach.setName(coach.getName());
        newCoach.setBirthDay(coach.getBirthDay());
    }
}
