package rikei.academy.view;

import rikei.academy.controller.UserController;
import rikei.academy.model.User;

public class Main {
    UserController userController = new UserController();
    public Main(){
        User currentUser = userController.getCurrentUser();
        if (currentUser == null){
            new ViewMainMenu().menu();
        }else {
            new ViewHome();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
