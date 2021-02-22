package com.company;

import Shop.ShopMain;
import Users.Customer;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        boolean lifeCycle = true;
        int option = -1;
        Scanner s = new Scanner(System.in);

        while (lifeCycle){
            System.out.println("Hello and welcome to the Electronic E-shop \n" +
                    "choose one option :\n" +
                    "1.Show all products \n" +
                    "2.go to Seller interface \n" +
                    "3.go to Customer interface \n" +
                    "4.for registration \n" +
                    "5.Exit");
            option = s.nextInt();
            switch (option){
                case 1:
                    ShopMain.show_all();
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 2:
                    ShopMain.interface_redirect(1);
                    break;
                case 3:
                    ShopMain.interface_redirect(2);
                    break;
                case 4:
                    ShopMain.interface_redirect(3);
                    break;
                case 5:
                    System.exit(0);
                    break;

                default:
                    break;
            }
        }

    }
}
