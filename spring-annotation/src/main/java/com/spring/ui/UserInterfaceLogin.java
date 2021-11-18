package com.spring.ui;

import com.spring.entities.Account;
import com.spring.service.MyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

@Component
public class UserInterfaceLogin {
    @Autowired
    private MyServices myServices;
    @Autowired
    private Account account;
    public  void loginApplication() {
        Scanner sc = new Scanner(System.in);
        int choice;


        while (true) {
            System.out.println("Choose 1 to Check Balance," +
                    "Choose 2 to deposit Money,Choose " +
                    "3 to withdraw,Choose 4 to transfer,choose 5 to see transaction History");
            System.out.println("6 to LOGOUT");
            choice = sc.nextInt();
            if (choice == 6) {
                System.out.println("logging out...");
                System.out.println("logged out Successfully");
                System.out.println();
                break;
            } else {

                switch (choice) {
                    case 1:
                        String accountNumber;
                        System.out.println("Please Provide the account Number ");
                        accountNumber = sc.next();
                        account.setAccountNumber(accountNumber);

                        myServices.checkBalanceService(account);
                        break;
                    case 2:

                        System.out.println("Please Provide the account Number ");
                        System.out.println("Amount to be deposited");
                        account.setAccountNumber(sc.next());
                        account.setAmount(sc.nextInt());

                        myServices.depositService(account);
                        break;
                    case 3:
                        System.out.println("Please Provide the account Number ");
                        System.out.println("Amount to be withdrawn");
                        account.setAccountNumber(sc.next());
                        account.setAmount(sc.nextInt());
                        myServices.withdrawService(account);
                        break;
                    case 4:
                        String accountNo1;
                        String accountNo2;
                        int amount;
                        System.out.println("Enter your account no.");
                        accountNo1 = sc.next();
                        System.out.println("Enter account no. to transfer funds");
                        accountNo2 = sc.next();
                        System.out.println("Enter amount to be  transferred");
                        amount = sc.nextInt();
                        if (accountNo1.equals(accountNo2)) {
                            System.out.println("You cannot transfer money to same account"
                            );
                        } else {
                            account.setAccountNumber(accountNo1);
                            account.setAmount(amount);
                            myServices.transferService(account, accountNo2);
                        }
                        break;
                    case 5:
                        System.out.println("Enter your account Number");
                        account.setAccountNumber(sc.next());
                        myServices.transactionService(account);
                        break;
                    default:
                        System.out.println("Choose no. between 1 to 5 for operation and 6 to logout the application");
                        break;

                }
            }
        }
    }
}
