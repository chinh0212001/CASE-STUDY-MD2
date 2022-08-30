package rikei.academy.view;

import rikei.academy.config.Config;
import rikei.academy.controller.CoachController;
import rikei.academy.controller.UserController;
import rikei.academy.model.manU.Coach;

import java.util.List;

public class ViewCoach {
    public UserController userController = new UserController();
    public CoachController coachController = new CoachController();
    public List<Coach> coachList = coachController.showListCoach();
    public void viewCoach(){
        System.out.println("1. Show list coach");
        System.out.println("2. Create coach");
        System.out.println("3. Delete coach");
        System.out.println("4. Detail coach");
        System.out.println("5. Edit coach");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                fromShowListCoach();
                break;
            case 2:
                fromCreateCoach();
                break;
            case 3:
                fromDeleteCoach();
                break;
            case 4:
                fromDetailCoach();
                break;
            case 5:
                fromEditCoach();
                break;
        }
    }

    private void fromEditCoach() {
        System.out.println("enter id: ");
        int idCoach = Config.scanner().nextInt();
        if (coachController.detailCoach(idCoach)==null){
            System.out.println("ko ton tai!!!");
        }else {
            Coach newCoach = coachController.detailCoach(idCoach);
            System.out.println("OLD NAME: "+ newCoach.getName());
            System.out.println("OLD BIRTHDAY: "+ newCoach.getBirthDay());
            System.out.println("Enter new coach: ");
            String newName = Config.scanner().nextLine();
            if (newName.trim().equals("")){
                newName = newCoach.getName();
            }
            System.out.println("Enter new brithDay: ");
            String newBrithDay = Config.scanner().nextLine();
            if (newBrithDay.trim().equals("")){
                newBrithDay = String.valueOf(newCoach.getBirthDay());
            }
            Coach coachNew = new Coach(newName,newBrithDay);
            coachController.EditCoach(idCoach,coachNew);
            fromShowListCoach();
        }
    }

    private void fromDetailCoach() {
        System.out.println("Enter id: ");
        int idCoach = Config.scanner().nextInt();
        if (coachController.detailCoach(idCoach)==null){
            System.out.println("No exist id coach");
        }else {
            Coach coach = coachController.detailCoach(idCoach);
            System.out.println(coach);
        }
        System.out.println("Enter phím bất kì để tiếp tục ---Enter back để thoát: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
            new ViewCoach();
        }
        viewCoach();
    }

    private void fromDeleteCoach() {
        int idCoach;
        while (true){
            System.out.println("Enter id: ");
            idCoach = Integer.parseInt(Config.scanner().nextLine());
            if (checkId(idCoach)){
                coachController.deleteCoach(idCoach);
                break;
            }else {
                System.out.println("ko tồn tại !!!");
            }
        }
        fromShowListCoach();
    }

    private boolean checkId(int id){
        for (int i = 0; i < coachList.size(); i++) {
          if (id == coachList.get(i).getId()){
              return true;
          }
        }
        return false;
    }

    private void fromCreateCoach(){
        int id;
        while (true){
            System.out.println("Enter id: ");
            id = Integer.parseInt(Config.scanner().nextLine());
            if (checkId(id)){
                System.out.println("id đã tồn tại: ");
            }else {
                break;
            }

        }
        System.out.println("Enter name coach: ");
        String name = Config.scanner().nextLine();
        System.out.println("Enter brithDay coach: ");
        int brithDay = Integer.parseInt(Config.scanner().nextLine());
        Coach coach = new Coach(id,name,brithDay);
        coachController.CreateCoach(coach);
        System.out.println("Create thành công !!!");
        System.out.println("ID======NAME====BIRTHDAY");
        for (int i = 0; i < coachList.size(); i++) {
            System.out.println(coachList.get(i).getId()
                    +"===="+coachList.get(i).getName()
                    +"===="+coachList.get(i).getBirthDay());
        }
        //back
        System.out.println("Enter phím bất kì để tiếp tục ---Enter back để thoát: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
            viewCoach();
        }
        fromCreateCoach();
    }

    public void fromShowListCoach() {
        System.out.println("ID======NAME====BIRTHDAY");
        for (int i = 0; i < coachList.size(); i++) {
            System.out.println(coachList.get(i).getId()
                    +"===="+coachList.get(i).getName()
                    +"===="+coachList.get(i).getBirthDay());
        }
//        viewCoach();
    }

}
