package models;

import database.UserDAO;

import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;

    public User(int id, String name, String username, String password, String email, String address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }
    public static User getUserByLogIn(String username, String password){
        return UserDAO.instance.logIn(username, password);
    }
    public static User signUp(String name, String username, String password, String email, String address){
        try {
            UserDAO.instance.insertUser(new User(-1, name, username, password, email, address));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return UserDAO.instance.logIn(username, password);
    }
    public void update(){
        try {
            UserDAO.instance.updateUser(this);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //Getter Setter
    public int getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
