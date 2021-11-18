package com.spring.entities;


import org.springframework.stereotype.Component;

@Component
public class User {
    private int userId;
    private String userName;
    private String userCity;
    private String userState;
    private String userPassword;

    public User() {
    }

    public User(int userId, String userName, String userCity, String userState, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userCity = userCity;
        this.userState = userState;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userCity='" + userCity + '\'' +
                ", userState='" + userState + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
