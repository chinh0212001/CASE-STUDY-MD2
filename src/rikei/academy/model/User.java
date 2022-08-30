package rikei.academy.model;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String username ;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean status;


    public User(int id, String name, String username, String email, String password, Set<Role> roles, boolean status) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }
    public User(int id, String name, String username, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                '}';
    }

    public RoleName getRoleName() {
        for(Role role : roles) {
            if(role.getRoleName() == RoleName.PM) return RoleName.PM;
            if(role.getRoleName() == RoleName.PLAYER) return RoleName.PLAYER;
            if(role.getRoleName() == RoleName.COACH) return RoleName.COACH;
        }
        return null;
    }
}
