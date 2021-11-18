package com.spring.ui;

import com.spring.entities.User;
import com.spring.service.MyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


@Component
public class UserInterfaceIntro {
    @Autowired
    MyServices myServices;
    @Autowired
    User user;
    @Autowired
    UserInterfaceLogin userInterfaceLogin;
    public void userInterfaceIntro()
    {
        Scanner sc=new Scanner(System.in);


        while(true) {
            System.out.println("JHARKHAND NATIONAL BANK");
            System.out.println();
            System.out.println("1)sign up");
            System.out.println("2)sign in");
            System.out.println("3)EXIT ");
            int choice;
            choice = sc.nextInt();

            if(choice==3)
            {
                break;
            }
            else {
                switch (choice) {
                    case 1:
                        String userName;
                        String userCity;
                        String userState;
                        String userPassword;
                        System.out.println("WELCOME TO SIGNIN PAGE");
                        System.out.println();
                        System.out.println("Enter your name");
                        userName=sc.next();
                        System.out.println("Enter your city");
                        userCity=sc.next();
                        System.out.println("Enter your state");
                        userState=sc.next();
                        System.out.println("Set your Password");
                        userPassword=sc.next();
                        user.setUserName(userName);
                        user.setUserCity(userCity);
                        user.setUserState(userState);
                        user.setUserPassword(userPassword);

                        myServices.createUserService(user);

                        break;
                    case 2:

                        String name;
                        String password;
                        System.out.println("Enter username");
                        name=sc.next();
                        System.out.println("Enter the password");
                        password=sc.next();
                        user.setUserName(name);
                        user.setUserPassword(password);

                        //checkUser is to check if login is correct or not it get 1 if correct
                        int checkUser;
                        checkUser=myServices.logInService(user);
                        if(checkUser==1)
                        {
                            userInterfaceLogin.loginApplication();

                        }
                        else
                        {
                            System.out.println("Wrong User Name or Password");
                        }
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Wrong Choice please select either 1 or 2");
                        break;

                }
            }
        }
    }
}
