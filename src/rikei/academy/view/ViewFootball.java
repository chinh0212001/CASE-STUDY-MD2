package rikei.academy.view;

import rikei.academy.config.Config;
import rikei.academy.controller.FootballController;
import rikei.academy.controller.UserController;
import rikei.academy.model.manU.FootballPlayer;

import java.util.List;

public class ViewFootball {
    public UserController userController = new UserController();
    public FootballController footballController = new FootballController();
    public List<FootballPlayer> playerList = footballController.showListFootball();
    public void viewFootball(){
        System.out.println("1. Show list player");
        System.out.println("2. Create player");
        System.out.println("3. Delete player");
        System.out.println("4. Detail player");
        System.out.println("5. Edit player");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                fromShowList();
                break;
            case 2:
                fromCreateFootball();
                break;
            case 3:
                fromDeleteFootball();
                break;
            case 4:
                fromDetailFootball();
                break;
            case 5:
                fromEditFootball();
                break;

        }
    }

    private void fromEditFootball() {
        System.out.println("Enter id: ");
        int idPlayer = Config.scanner().nextInt();
        if (footballController.detailFootball(idPlayer)==null){
            System.out.println("ko ton tai !");
        }else {
            FootballPlayer player = footballController.detailFootball(idPlayer);
            System.out.println("OLD NAME: "+ player.getName());
            System.out.println("OLD BIRTHDAY: "+ player.getBirthDay());
            System.out.println("OLD NUMBER: "+ player.getNumber());
            System.out.println("OLD COUNTRY: "+ player.getCountry());
            System.out.println("Enter new name: ");
            String newName = Config.scanner().nextLine();
            if (newName.trim().equals("")){
                newName = player.getName();
            }
            System.out.println("Enter new birthday: ");
            String newBirthDay = Config.scanner().nextLine();
            if (newBirthDay.trim().equals("")){
                newBirthDay = String.valueOf(player.getBirthDay());
            }
            System.out.println("Enter new number: ");
            String newNUmber = Config.scanner().nextLine();
            if (newNUmber.trim().equals("")){
                newNUmber = String.valueOf(player.getNumber());
            }
            System.out.println("Enter new country: ");
            String newCountry = Config.scanner().nextLine();
            if (newCountry.trim().equals("")){
                newCountry = player.getCountry();
            }
            FootballPlayer newPlayer = new FootballPlayer(newName,newBirthDay,newNUmber,newCountry);
            footballController.EditFootball(idPlayer,newPlayer);
            fromShowList();
        }

    }

    private void fromDetailFootball() {
        System.out.println("Enter id: ");
        int idPlayer = Config.scanner().nextInt();
        if (footballController.detailFootball(idPlayer)==null){
            System.out.println("NO exist id player");
        }else {
            FootballPlayer footballPlayer = footballController.detailFootball(idPlayer);
            System.out.println(footballPlayer);
        }
        System.out.println("Enter phím bất kì để tiếp tục ---Enter back để thoát: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
           new ViewFootball();
        }
        viewFootball();
    }

    public void fromDeleteFootball() {
        int idFootball;
        while (true){
            System.out.println("Enter id: ");
            idFootball = Integer.parseInt(Config.scanner().nextLine());
            if (checkId(idFootball)){
                footballController.deleteFootball(idFootball);
                break;
            }else {
                System.out.println("ko tồn tại !!!");
            }
        }
        fromShowList();
    }

    private void fromCreateFootball() {
        int id;
        while (true){
            System.out.println("Enter id: ");
            id = Integer.parseInt(Config.scanner().nextLine());
            if (checkId(id)){
                System.out.println("id đã tồn tại :))");
            }else {
                break;
            }
        }
        System.out.println("Enter name player: ");
        String name = Config.scanner().nextLine();
        System.out.println("Enter birthDay player: ");
        int birthDay = Integer.parseInt(Config.scanner().nextLine());
        System.out.println("Enter number player: ");
        int number = Integer.parseInt(Config.scanner().nextLine());
        System.out.println("Enter country player: ");
        String country = Config.scanner().nextLine();
        FootballPlayer footballPlayer = new FootballPlayer(id,name,birthDay,number,country);
        footballController.CreateFootball(footballPlayer);
        System.out.println("Create thành công !!!");
        System.out.println("ID----NAME----BIRTHDAY----NUMBER----COUNTRY");
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i).getId()
                    +"----"+playerList.get(i).getName()
                    +"----"+playerList.get(i).getBirthDay()
                    +"----"+playerList.get(i).getNumber()
                    +"----"+playerList.get(i).getCountry());
        }
        //back
        System.out.println("Enter phím bất kì để tiếp tục ---Enter back để thoát: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
            viewFootball();
        }
        fromCreateFootball();
    }

    public void fromShowList() {
        System.out.println("ID----NAME----BIRTHDAY----NUMBER----COUNTRY");
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i).getId()
            +"----"+playerList.get(i).getName()
            +"----"+playerList.get(i).getBirthDay()
            +"----"+playerList.get(i).getNumber()
            +"----"+playerList.get(i).getCountry());
        }
//         viewFootball();
    }
    private boolean checkId(int id){
        for (int i = 0; i < playerList.size(); i++) {
            if (id == playerList.get(i).getId()){
                return true;
            }
        }
        return false;
    }

}
