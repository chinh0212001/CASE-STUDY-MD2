package rikei.academy.service.user;

import rikei.academy.config.Config;
import rikei.academy.model.Role;
import rikei.academy.model.RoleName;
import rikei.academy.model.User;
import rikei.academy.service.role.RoleServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService{
    static String PATH_USER = "src/rikei/academy/data/user.txt";
    static String PATH_LOGIN = "src/rikei/academy/data/user_login.txt";
    static Config<List<User>> config = new Config<>();
    static List<User> userList = config.read(PATH_USER);
    static {
        if (userList == null || userList.size() == 0) {
            userList = new ArrayList<>();
            Set<Role> roles = new HashSet<>();
            roles.add(new RoleServiceIMPL().findByRoleName(RoleName.COACH));
            userList.add(
                    new User(
                            0,
                            "Admin",
                            "admin",
                            "admin@admin.admin",
                            "admin",
                            roles
                    )
            );
        }
    }

    @Override
    public List<User> findAll() {
        config.write(PATH_USER,userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        updateData();
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id){
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id){
                 userList.remove(i);
            }
        }
    }

    @Override
    public boolean existByUsername(String username) {
        System.out.println(userList);
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : userList){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (User user : userList){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        User user = new Config<User>().read(PATH_LOGIN);
        if (user == null){
            return null;
        }
        return findByUsername(user.getUsername());
    }

    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().write(PATH_LOGIN,user);

    }

    @Override
    public User findByUsername(String username) {
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public int getLastId() {
        return userList.get(userList.size()-1).getId()+1;
    }

    @Override
    public void updateData() {
        config.write(PATH_USER,userList);

    }

    @Override
    public void changeRole(int id, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        findById(id).setRoles(roles);
        updateData();

    }

    @Override
    public void changeStatus(int id) {
        User user = findById(id);
        user.setStatus(!user.isStatus());
        updateData();

    }


}
