package com.techelevator;

import com.techelevator.view.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class CaTEringCapstoneCLI {

    private Accounting accounting = new Accounting();
    private InventoryMap inventoryMap = new InventoryMap();

    public void feedMoney(BigDecimal moneyDeposited) {
        accounting.addMoney(moneyDeposited);
        String moneyDepositedStr = "$" + moneyDeposited + ".00";
        System.out.println(moneyDepositedStr);
    }

    public void subtractMoney(BigDecimal moneySpent) {
        accounting.subtractPrice(moneySpent);
        String moneySubtractedStr = "- $" + moneySpent;
        System.out.println(moneySubtractedStr);
    }

    public Accounting getMoneyMethods() {
        return accounting;
    }

    public BigDecimal getBalanceInCents() {
        BigDecimal balance = accounting.balance;
        return balance;
    }

    // *************************** MENU ***************************

    private Menu menu;
    private Scanner scanner;

    public CaTEringCapstoneCLI(Menu menu) {
        this.menu = menu;
        this.scanner = new Scanner(System.in);
        this.inventoryMap.restockInventory();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
        cli.run();
    }

    public void run() {

        System.out.println("\nWelcome to the CaTEring Machine!\n");
        menu.mainMenu();
        boolean keepGoing = true;

        do {
            
            String mainMenuInput = scanner.nextLine();

            if (mainMenuInput.equalsIgnoreCase("D")) {
                System.out.println(inventoryMap.getMapStr());
                menu.mainMenu();
            } else if (mainMenuInput.equalsIgnoreCase("P")) {
                
                do {
                    this.menu.purchaseMenu();
                    String purchaseLevelInput = scanner.nextLine();
    
                    if (purchaseLevelInput.equalsIgnoreCase("M")) {
                        System.out.println();
                        System.out.print("Please Insert U.S. Dollar Bill Amount: (1), (5), (10), or (20): ");
                        Scanner feedMoneyInput = new Scanner(System.in);
    
                        try {
                            int moneyDeposited = Integer.parseInt(feedMoneyInput.nextLine());
                            BigDecimal previousBalance = accounting.getBalance().divide(new BigDecimal("100"));
    
                            if (moneyDeposited == 1) {
                                accounting.addMoney(new BigDecimal("100"));
                                System.out.println("\n" + accounting.balanceString());
                                AuditLog.addToLog("FEED MONEY:                  ", previousBalance, 
                                accounting.getBalance().divide(new BigDecimal("100")));
                            } else if (moneyDeposited == 5) {
                                accounting.addMoney(new BigDecimal("500"));
                                System.out.println("\n" + accounting.balanceString());
                                AuditLog.addToLog("FEED MONEY:                  ", previousBalance, 
                                accounting.getBalance().divide(new BigDecimal("100")));
                            } else if (moneyDeposited == 10) {
                                accounting.addMoney(new BigDecimal("1000"));
                                System.out.println("\n" + accounting.balanceString());
                                AuditLog.addToLog("FEED MONEY:                  ", previousBalance, 
                                accounting.getBalance().divide(new BigDecimal("100")));
                            } else if (moneyDeposited == 20) {
                                accounting.addMoney(new BigDecimal("2000"));
                                System.out.println("\n" + accounting.balanceString());
                                AuditLog.addToLog("FEED MONEY:                  ", previousBalance, 
                                accounting.getBalance().divide(new BigDecimal("100")));
                            } else {
                                System.out.println("\nPlease Insert Valid Currency");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\nPlease Insert Valid Currency");
                        }
                        
                    } else if (purchaseLevelInput.equalsIgnoreCase("S")) {
                        BigDecimal previousBalance = accounting.getBalance().divide(new BigDecimal("100"));
                        System.out.println(inventoryMap.getMapStr());
                        System.out.print("Please enter the item code: ");
    
                        try {
                            Scanner selectionScanner = new Scanner(System.in);
                            String selectedProduct = selectionScanner.nextLine();
    
                            Products product = inventoryMap.getInventory().get(selectedProduct);
                            System.out.println("\n" + product.getName());
    
                            if (product.getQuantity() == 0) {
                                System.out.println("Out of Stock. Please choose another item.");
    
                            } else if (accounting.getBalance().compareTo(product.getPrice()) < 0) {
                                System.out.println("Insufficient Funds");
    
                            } else if (product.getQuantity() > 0) {
                                // REMOVE QUANTITY
                                product.setQuantity(product.getQuantity() - 1);
                                AuditLog.addToLog(product.getName() + " " +  product.getItemSlot()+"        ", previousBalance, accounting.getBalance().divide(new BigDecimal("100")));
    
                                // REMOVE PRICE FROM BALANCE
                                BigDecimal price = product.getPrice();
                                accounting.subtractPrice(price);
    
                                // PRINT ITEM CLASS MESSAGE
                                if (selectedProduct.contains("1")) {
                                    System.out.println("Munchy, Munchy, so Good! \n");
                                } else if (selectedProduct.contains("2")) {
                                    System.out.println("Sandwich So Delicious, Yum! \n");
                                } else if (selectedProduct.contains("3")) {
                                    System.out.println("Drinky, Drinky, Slurp Slurp! \n");
                                } else if (selectedProduct.contains("4")) {
                                    System.out.println("Sugar, Sugar, so Sweet! \n");
                                }

                                System.out.println("Current Balance: $" + accounting.getBalance().divide(new BigDecimal("100.00")));
                            }
                            } catch(Exception e){
                                System.out.println("Invalid Input");
                                continue;
                            }
                        } else if (purchaseLevelInput.equalsIgnoreCase("F")) {
                            System.out.println(accounting.changeReturn());
                            keepGoing = false;
                        } else {
                            System.out.println("Invalid Command!");
                        }
                } while (keepGoing);
                } else if (mainMenuInput.equalsIgnoreCase("E")) {
                    System.out.println();
                    System.out.println("Thank you for your business!");
                    System.out.println();
                    keepGoing = false;
                } else {
                    System.out.println("Invalid Command!");
                }
            } while (keepGoing) ;
        }
    }
