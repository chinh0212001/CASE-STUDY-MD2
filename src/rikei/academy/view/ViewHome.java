package rikei.academy.view;

import rikei.academy.config.Config;
import rikei.academy.controller.UserController;
import rikei.academy.model.RoleName;
import rikei.academy.model.User;

import java.util.ArrayList;

public class ViewHome {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();

    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();
    public ViewHome(){
        switch (roleName){
            case PM:
                menuPM();
                break;
            case COACH:
                menuCoach();
                break;
            case PLAYER:
                menuPlayer();
                break;
        }
    }

    private void menuPlayer() {
        System.out.println("Hello Player: "+ roleName + ": "+ currentUser.getName());
        System.out.println("1. log Out");
        System.out.println("2. list player");
        System.out.println("3. list Coach");
        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewFootball().fromShowList();
                break;
            case 3:
                new ViewCoach().fromShowListCoach();
                break;

        }


        menuPlayer();
    }

    public void menuPM() {
        System.out.println("Hello PM: "+ roleName + ": "+currentUser.getName());
        System.out.println("1. LOg Out");
        System.out.println("2. show list player");
        System.out.println("3. Delete player");
        System.out.println();


        switch (Config.scanner().nextInt()) {
            case 1:
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewFootball().fromShowList();
                break;
            case 3:
                new ViewFootball().fromDeleteFootball();
                break;
            case 4:
                new ViewCoach().fromShowListCoach();
                break;

        }
        menuPM();

    }

    public void menuCoach() {
        System.out.println("Hello Coach: " + roleName + ": " + currentUser.getName());
        System.out.println("----WELCOME MANCHESTER UTD----");
        System.out.println("1. Log Out");
        System.out.println("2. player");
        System.out.println("3. Coach");

        int choice = Config.scanner().nextInt();

        switch (choice) {
            case 1:
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewFootball().viewFootball();
                break;
            case 3:
                new ViewCoach().viewCoach();
                break;
        }

        menuCoach();
    }
}
