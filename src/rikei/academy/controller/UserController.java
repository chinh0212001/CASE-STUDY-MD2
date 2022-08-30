package rikei.academy.controller;

import rikei.academy.dto.request.SignInDTO;
import rikei.academy.dto.request.SignUpDTO;
import rikei.academy.dto.response.ResponseMessenger;
import rikei.academy.model.Role;
import rikei.academy.model.RoleName;
import rikei.academy.model.User;
import rikei.academy.service.role.IRoleService;
import rikei.academy.service.role.RoleServiceIMPL;
import rikei.academy.service.user.IUserService;
import rikei.academy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
    IRoleService roleService = new RoleServiceIMPL();

    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existByUsername(signUpDTO.getUsername())) {
            return new ResponseMessenger("user_exited");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role : strRole) {
            switch (role) {
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                case "coach":
                    roles.add(roleService.findByRoleName(RoleName.COACH));
                    break;
                case "player":
                    roles.add(roleService.findByRoleName(RoleName.PLAYER));
                    break;
                default:
                    return new ResponseMessenger("invalid_role");
            }
        }
        User user = new User(signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles);
        userService.save(user);
        return new ResponseMessenger("success");
    }

    public ResponseMessenger login(SignInDTO signInDTO){
        if (!userService.checkLogin(signInDTO.getUsername(),signInDTO.getPassword())){
            return new ResponseMessenger("Login_failure");
        }
        User login = userService.findByUsername(signInDTO.getUsername());
        userService.saveCurrentUser(login);
        return  new ResponseMessenger("Login_success");
    }

    public User getCurrentUser(){
        return userService.getCurrentUser();

    }
    public void logOut(){
        userService.saveCurrentUser(null);
    }
}

