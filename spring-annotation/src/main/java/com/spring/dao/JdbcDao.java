package com.spring.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class JdbcDao {


    public static final String URLTOCONNECT="jdbc:mysql://localhost:3303/Bank";

    public static final String USERNAME = "root";

    public static final String USERPASSWORD = "root";

    String qry;


    Connection dbCon;

    Statement theStatement;

    ResultSet theResultSet;

    PreparedStatement thePreparedStatement;

    public JdbcDao() {
        try {
//          Load the DB Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            dbCon = DriverManager.getConnection(URLTOCONNECT, USERNAME, USERPASSWORD);
            theStatement = dbCon.createStatement();

//			System.out.println("Connected to the database now...");


        } catch (ClassNotFoundException e) {
            System.out.println("Can't load the driver : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Can't connect to the database : " + e.getMessage());
        }











//Transfer the amount from one account to the another account


        }


    public void checkBalance(String accountNo) {
        qry = "Select amount from Account where accountNumber='" + accountNo + "'";
        String amount = "";
        try {
            theResultSet = theStatement.executeQuery(qry);
            while (theResultSet.next()) {
                amount = theResultSet.getString("amount");
                System.out.println("Available Balance: " + theResultSet.getString("amount"));
            }
            //Setting acc no,action and date for transaction detail
            String Action = "Checked Balance " + amount;
            java.util.Date date = new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            String transactionDate = formatter.format(date);
            JdbcDao app=new JdbcDao();
            this.transactionImpl(accountNo, Action, transactionDate);

        } catch (SQLException e) {
            System.out.println("Can't execute the query : " + e.getMessage());
        }
    }

    public void deposit(String accountNo, int amount) {
        try {

            thePreparedStatement = dbCon.prepareStatement("select amount from Account where accountNumber=?");
            int totalAmount = amount;

            thePreparedStatement.setString(1, accountNo);

            theResultSet = thePreparedStatement.executeQuery();

            while (theResultSet.next()) {
                totalAmount = totalAmount + theResultSet.getInt("amount");
            }

            qry = "Update Account set amount=? where accountNumber=?";
            thePreparedStatement = dbCon.prepareStatement(qry);
            thePreparedStatement.setInt(1, totalAmount);
            thePreparedStatement.setString(2, accountNo);
            if (thePreparedStatement.executeUpdate() > 0) {
                System.out.println("Amount " + amount + " Deposited Successfully to  A/c " + accountNo);
                System.out.println("Your Net Balance: " + totalAmount);
                System.out.println();

                //Setting acc no,action and date for transaction detail
                String Action = "Deposited " + amount;
                java.util.Date date = new java.util.Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                String transactionDate = formatter.format(date);

                this.transactionImpl(accountNo, Action, transactionDate);
            }

        } catch (SQLException e) {
            System.out.println("Sql exception occured");
        }

    }

    public void withdraw(String accountNo, int amount) {
        try {

            thePreparedStatement = dbCon.prepareStatement("select amount from Account where accountNumber=?");
            int totalAmount = amount;

            thePreparedStatement.setString(1, accountNo);

            theResultSet = thePreparedStatement.executeQuery();
            int check = 0;
            while (theResultSet.next()) {
                if (theResultSet.getInt("amount") < amount) {
                    System.out.println("You dont have enough balance check your balance by typing one");
                    check = 1;
                    break;
                } else {
                    totalAmount = -totalAmount + theResultSet.getInt("amount");
                }
            }
            if (check == 0) {
                qry = "Update Account set amount=? where accountNumber=?";
                thePreparedStatement = dbCon.prepareStatement(qry);
                thePreparedStatement.setInt(1, totalAmount);
                thePreparedStatement.setString(2, accountNo);
                if (thePreparedStatement.executeUpdate() > 0) {
                    System.out.println("Amount " + amount + " withdrawn Successfully from  A/c " + accountNo);
                    System.out.println("Your Net Balance: " + totalAmount);
                    System.out.println();


                    //Setting acc no,action and date for transaction detail
                    String Action = "Withdrawn " + amount;
                    java.util.Date date = new java.util.Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    String transactionDate = formatter.format(date);
                    this.transactionImpl(accountNo, Action, transactionDate);
                }

            }
        } catch (SQLException e) {
            System.out.println("Sql exception occured during withdrawal");
        }

    }
    public void transfer(String accountNo1,String accountNo2,int amount)
    {

        this.withdraw(accountNo1,amount);
        this.deposit(accountNo2,amount);
//Setting acc no,action and date for transaction detail
        String Action="Transffered "+Integer.toString(amount)+"to  A/c "+accountNo2;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String transactionDate = formatter.format(date);
        this.transactionImpl(accountNo1,Action,transactionDate);

    }
    public void createUser(String userName,String userCity,String userState,String userPassword)
    {
        try {
            qry="insert into user(user_name,user_city,user_state,user_password) values(?,?,?,?)";
            thePreparedStatement=dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1,userName);
            thePreparedStatement.setString(2,userCity);
            thePreparedStatement.setString(3,userState);
            thePreparedStatement.setString(4,userPassword);
            if(thePreparedStatement.executeUpdate()>0)
            {
                System.out.println("User added Successfully");
            }
        } catch (SQLException e) {
            System.out.println("Exception inside userSign");
        }


    }
    public int logInCheck(String userName,String userPassword)
    {
        //it is 1 if credential is correct else it will remain 0
        int checkLogIn=0;
        qry="select user_name from user where user_name=? and user_password=?";
        try {
            thePreparedStatement = dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1, userName);
            thePreparedStatement.setString(2, userPassword);
            theResultSet = thePreparedStatement.executeQuery();
            if (!theResultSet.next()) {
                checkLogIn=0;
            } else {
                checkLogIn=1;
                System.out.println("Succussefully Logged in with username:  " + userName);
            }
        }catch (SQLException e) {
                System.out.println("Exception inside login");
            }

        return checkLogIn;


            }

    public void transaction(String accountName)
    {

        try {
            qry="select * from transactions where accountNumber=?";
            thePreparedStatement = dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1,accountName);
            theResultSet = thePreparedStatement.executeQuery();
            if(!theResultSet.next())
            {
                System.out.println("NO Transaction History");
            }
            else {
                while (theResultSet.next()) {
                    System.out.println(theResultSet.getString("accountNumber") + "  " + theResultSet.getString("ActionTaken") +
                            "  " + theResultSet.getString("Date"));
                    System.out.println();
                }
            }


        } catch (SQLException e) {
            System.out.println("Exception occured inside transaction");
        }


    }

    public void transactionImpl(String accountName,String Action,String date) {
        try {
            qry = "insert into transactions values(?,?,?)";
            thePreparedStatement = dbCon.prepareStatement(qry);
            thePreparedStatement.setString(1, accountName);
            thePreparedStatement.setString(2, Action);
            thePreparedStatement.setString(3, date);
            if (thePreparedStatement.executeUpdate() > 0) {
                System.out.println("Your transaction Details updated:  ");
            }
        } catch (SQLException e) {
            System.out.println("Exception inside transactionImpl");
        }
    }
}



