package com.spring.entities;


import org.springframework.stereotype.Component;

@Component
public class Transaction {
    private String accountNumber;
    private String action;
    private String date;

    public Transaction() {
    }

    public Transaction(String accountNumber, String action, String date) {
        this.accountNumber = accountNumber;
        this.action = action;
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", action='" + action + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
