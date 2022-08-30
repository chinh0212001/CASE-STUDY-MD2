package rikei.academy.view;

import rikei.academy.config.Config;
import rikei.academy.controller.UserController;
import rikei.academy.dto.response.ResponseMessenger;
import rikei.academy.model.RoleName;
import rikei.academy.model.User;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("list coach");


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
        System.out.println("4. User Manage");
        System.out.println("5. Change password");

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
            case 4:
                 fromUserManage();
                break;
            case 5:
                fromChangePassword();
                break;
        }

        menuCoach();
    }

    private void fromUserManage() {
        System.out.println("Menu");
        System.out.println("1. Show list Player");
        System.out.println("2. Delete player");
        System.out.println("3. change role");
        System.out.println("4. Block player");
        System.out.println("5. back");

        int choice = Config.getValiInteger();
       switch (choice){
           case 1:
               fromShowListPlayer();
               break;
           case 2:
               fromDeletePlayer();
               break;
           case 3:
               fromChangeRole();
               break;
           case 4:
               fromBlockPlayer();
               break;
           case 5:
               return;
           default:
               System.out.println("Invalid choice");


       }
       fromUserManage();
    }

    private void fromBlockPlayer() {
        fromShowListPlayer();
        System.out.println("Enter id player to block: ");
        int id = Config.getValiInteger();
        ResponseMessenger messenger = userController.blockUser(id);
        switch (messenger.getMessage()){
            case "not_fond":
                System.out.println(" id not found");
                break;
            case "blocked":
                System.out.println("you just block player id"+id);
                break;
            case "unblocked":
                System.out.println("you just unblocked player id"+id);
        }
    }

    private void fromChangeRole() {
        fromShowListPlayer();
        System.out.println("Enter id of player to change role: ");
        int id = Config.getValiInteger();
        System.out.println("Enter role to change (pm/player)");
        String roleName = Config.scanner().nextLine();
        ResponseMessenger messenger = userController.changeRole(id,roleName);
        switch (messenger.getMessage()){
            case "success":
                System.out.println("Change role successfully!");
                break;
            case "invalid_role":
                System.out.println("Invalid role!");
                break;
            case "not_found":
                System.out.println("ID not found!");
        }
    }

    private void fromDeletePlayer() {
        fromShowListPlayer();
        System.out.println("Enter id of player to delete: ");
        int id = Config.getValiInteger();
        ResponseMessenger messenger = userController.deleteUser(id);
        switch (messenger.getMessage()){
            case "success":
                System.out.println("Delete player successfully");
                break;
            case "not_found":
                System.out.println("ID not found");
        }
    }

    private void fromShowListPlayer() {
        List<User> users = userController.getUserList();
        System.out.println(users);
        System.out.printf("%3s   %-12s%-7s %s\n","ID","USERNAME","ROLE","STATUS");
        for (User user : users){
            System.out.printf("%3s   %-12s %-7s %s\n", user.getId(), user.getUsername(), user.getRoleName(), (user.isStatus() ? "BLOCKED" : "NOT BLOCKED"));
        }
    }

    private void fromChangePassword() {

        String oldPassword;
        while (true){
            System.out.println("Enter your old password: ");
            oldPassword = Config.scanner().nextLine();
            if (oldPassword.matches("[a-zA-Z0-9]{1,40}")){
                break;
            }else {
                System.out.println("Invalid password ,try again!");
            }
        }
        System.out.println("Enter your new password: ");
        String newPassword = Config.scanner().nextLine();
        System.out.println("Repeat the new password: ");
        String newPassword2 = Config.scanner().nextLine();

        if (!newPassword.equals(newPassword2)){
            System.out.println("Repeat password incorrect");
        }else {
            ResponseMessenger messenger = userController.changePassword(oldPassword,newPassword);
            switch (messenger.getMessage()){
                case "not_match":
                    System.out.println("Old password does not matches!");
                    break;
                case "success":
                    System.out.println("Change password successfully!");
                    userController.logOut();
                    new ViewMainMenu().menu();
            }
        }
    }

}
