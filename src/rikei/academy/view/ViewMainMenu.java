package rikei.academy.view;

import rikei.academy.config.Config;
import rikei.academy.controller.UserController;
import rikei.academy.dto.request.SignInDTO;
import rikei.academy.dto.request.SignUpDTO;
import rikei.academy.dto.response.ResponseMessenger;
import rikei.academy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();

    public void menu(){

        System.out.println("-------Menu--------");
        System.out.println("1. Show user list");
        System.out.println("2. Register");
        System.out.println("3. Login");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                fromShowListUser();
                break;
            case 2:
                fromRegister();
                break;
            case 3:
                fromLogin();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menu();
    }

    private void fromLogin() {
        //username
        String username;
        while (true){
            System.out.println("Enter username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,30}")){
                break;
            }else {
                System.out.println("Invalid username , try again!");
            }
        }
        //password
        String password;
        while (true){
            System.out.println("Enter password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,40}")){
                break;
            }else {
                System.out.println("Invalid password, try again!");
            }
        }
        SignInDTO signInDTO = new SignInDTO(username,password);
        ResponseMessenger responseMessenger = userController.login(signInDTO);
        switch (responseMessenger.getMessage()){
            case "Login_success":
               System.out.println("Login successful!");
                new ViewHome();
                break;
            case "Login_failure":
                System.out.println("Username or password is incorrect!");
                break;
            case "blocked":
                System.err.println("This user is blocked!!@");
        }


    }

    private void fromRegister() {
        System.out.println("=========REGISTER=========");
        //id
        int id;
        if (userList.isEmpty()){
            id = 1;
        }else {
            id = userList.get(userList.size()-1).getId()+1;
        }
        //name
        String name;
        while (true){
            System.out.println("Enter name: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]{1,30}")){
                break;
            }else {
                System.out.println("Invalid name , try again");
            }
        }
        //username
        String username;
        while (true){
            System.out.println("Enter username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,30}")){
                break;
            }else {
                System.out.println("Invalid username , try again!");
            }
        }
        //email
        String email;
        while (true){
            System.out.println("Enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches("^(.+)@(.+)$")){
                break;
            }else {
                System.out.println("Invalid email , try again !");
            }
        }
        //password
        String password;
        while (true){
            System.out.println("Enter password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,40}")){
                break;
            }else {
                System.out.println("Invalid password , try again ! ");
            }
        }
        //role
        System.out.println("Enter Role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id,name,username,email,password,strRole);
        ResponseMessenger responseMessenger = userController.register(signUpDTO);

        switch (responseMessenger.getMessage()){
            case "user_existed":
                System.out.println("Username already exists");
                break;
            case "email_existed":
                System.out.println("email already exists");
                break;
            case "invalid_role":
                System.out.println("Invalid_role already exists");
                break;
            case "success":
                System.out.println("Register success");
        }
    }

    private void fromShowListUser() {
        System.out.println(userList);
        System.out.printf("%-15s%s%n","User","Role");
        for (User user : userList ) {
            System.out.printf("%-15s%s%n",user.getUsername(),new ArrayList<>(user.getRoles()).get(0).getRoleName());
        }
    }
}
