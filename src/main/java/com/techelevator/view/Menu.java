package com.techelevator.view;

import com.techelevator.CaTEringCapstoneCLI;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class  Menu {

    Accounting moneyAccount = new Accounting();
        // MAIN MENU
        public void mainMenu() {
            System.out.println("(D) Display CaTEring Items");
            System.out.println("(P) Purchase");
            System.out.println("(E) Exit");
            System.out.println();
            System.out.print("Please choose an option above: ");
        }

        // SUB LEVEL FOR PURCHASE
        public void purchaseMenu() {
            System.out.println();
            System.out.println("(M) Insert Money");
            System.out.println("(S) Select Item");
            System.out.println("(F) Finish Transaction");
            System.out.println();
            System.out.print("Please choose an option above: ");
        }
}
