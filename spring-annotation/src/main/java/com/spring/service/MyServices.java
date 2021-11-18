package com.spring.service;


import com.spring.dao.JdbcDao;
import com.spring.entities.Account;
import com.spring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyServices {


    @Autowired
    private JdbcDao jdbcDao;

    public void checkBalanceService(Account account)
    {
        jdbcDao.checkBalance(account.getAccountNumber());

    }
    public void depositService(Account account)
    {
        jdbcDao.deposit(account.getAccountNumber(),account.getAmount());

    }
    public void withdrawService(Account account)
    {
        jdbcDao.withdraw(account.getAccountNumber(),account.getAmount());
    }

    public void createUserService(User user)
    {
        jdbcDao.createUser(user.getUserName(),user.getUserCity(),user.getUserState(),user.getUserPassword());
    }
    public int logInService(User user)
    {
        int check=jdbcDao.logInCheck(user.getUserName(),user.getUserPassword());
        //Check is 1 if login credentials are write otherwise it returns 0
        return check;
    }
    //***********************************************************
    static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }
//**********************************************************************
    public void transferService(Account account,String accountNo2)
    {
        int amount;
        amount=account.getAmount();
        String netAmount=Integer.toString(amount);

        if(isNumber(netAmount)) {
            jdbcDao.transfer(account.getAccountNumber(), accountNo2, account.getAmount());
        }
        else
        {
            System.out.println("Amount should be number");
        }

    }
    public void transactionService(Account account)
    {
        jdbcDao.transaction(account.getAccountNumber());
    }





}
