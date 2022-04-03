package com.vendingMachine;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Testing the Vending machine
 * Created by Vinod Pasalkar on 02/04/2022.
 */

public class VendingMachineImpl implements VendingMachineAPI {
    static Scanner sc = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("#.##");

    String selectedItemName;
    double selectedItemCost;
    double insertedAmount;
    double balanceToBePaid;
    String availabilityMessage= "";
    String paymentMessage = "";
    String stockMessage = "In stock";

    CoinInventory coinInventory = new CoinInventory();
    ItemInventory itemInventory = new ItemInventory();

    public static void main(String[] args) {
        VendingMachineImpl vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
        try {
            vendingMachine.printOperationsInConsole();
            int operation = sc.nextInt();
            boolean exit = false;
            while (operation != 5 && !exit) {
                switch (operation) {
                    case 1:
                        //Currently, hard coded test data is used as the unit tests can not accept run time inputs
                        vendingMachine.buyAnItem("COKE",1,1,2,3,4);
                        break;
                    case 2:
                        //Currently, hard coded test data is used as the unit tests can not accept run time inputs
                        vendingMachine.cancelRequestAndRefund("COKE",1,1,2,3,4);
                        break;
                    case 3:
                        vendingMachine.getInventoryData();
                        break;
                    case 4:
                        vendingMachine.reset();
                        break;
                    case 5:
                        exit = true;
                        sc.close();
                    default:
                        System.out.println("INCORRECT OPERATION SELECTED. SELECT AN APPROPRIATE ACTION");
                }
                vendingMachine.printOperationsInConsole();
                operation = sc.nextInt();
            }
        } catch (InputMismatchException | MachineOutOfCashException e) {
            System.out.println("Incorrect input " + e);
        }

    }

    public void initialize() {
        System.out.println("======INITIALIZE THE VENDING MACHINE BALANCE NOW======");
        coinInventory.initialize();
        itemInventory.initialize();
    }

    public void buyAnItem(String item,int quantity,int PENNY , int NICKEL, int DIME,int QUARTER) throws MachineOutOfCashException {
        enterItemToBuy(item,quantity);
        insertCoins(PENNY,NICKEL,DIME,QUARTER);
        dispenseItemAndChange(item,quantity);
        getInventoryData();
    }

    public void buyAnItemNoEnoughMoney(String item, int quantity,int PENNY , int NICKEL, int DIME, int QUARTER) {
        enterItemToBuy(item,quantity);
        insertCoins(PENNY,NICKEL,DIME,QUARTER);
    }

    public void topUpItem(String item,int quantity) {
        itemInventory.addItem(item,quantity);

    }

    public void cancelRequestAndRefund(String item,int quantity,int PENNY , int NICKEL, int DIME,int QUARTER) throws MachineOutOfCashException {
        itemInventory.addBackItem(item,quantity);
        balanceToBePaid = Double.valueOf(df.format(selectedItemCost));
        coinInventory.dispenseCoins(balanceToBePaid);
        getInventoryData();
    }

    public void getInventoryData() {
        System.out.println("INVENTORY BALANCE: " + coinInventory.inventoryBalance);
        System.out.println("REMAINING ITEMS:");
        itemInventory.printItems();
    }

    public void reset() {
        System.out.println("======RESETTING THE VENDING MACHINE=====");
        coinInventory.reset();
        itemInventory.reset();
        getInventoryData();
    }

    public void stockFinishedFor(String selectedItemName) {
        itemInventory.stockOverForItem(selectedItemName);
        stockMessage = "ITEM OUT OF STOCK";
        getInventoryData();
    }

    private void enterItemToBuy(String ItemName,int quantity) {
        selectedItemName = ItemName;
        System.out.print("======ENTER THE ITEM NAME NOW======");
        //Commented below line as the hard coded tests can not accept run time inputs
        //selectedItemName = sc.next();
        checkItemAvailability(ItemName,quantity);
    }

    void insertCoins(int PENNY , int NICKEL, int DIME,int QUARTER) {
        System.out.println("======INSERT THE COINS NOW=======");
        insertedAmount = coinInventory.addCoins(PENNY,NICKEL,DIME,QUARTER);
        System.out.println("INSERTED AMOUNT: " + df.format(insertedAmount));
        isPaidEnough(PENNY,NICKEL,DIME,QUARTER);
    }

    void dispenseItemAndChange(String item,int quantity) throws MachineOutOfCashException {
        System.out.println("======DISPENSING THE ITEM NOW=======");
        itemInventory.dispenseItem(item,quantity);

        balanceToBePaid = Double.valueOf(df.format(insertedAmount - selectedItemCost));
        coinInventory.dispenseCoins(balanceToBePaid);
    }

    private void printOperationsInConsole() {
        System.out.println("=====SELECT AN OPERATION TO PERFORM=====");
        System.out.println("ENTER 1 TO BUY AN ITEM");
        System.out.println("ENTER 2 TO CANCEL THE REQUEST");
        System.out.println("ENTER 3 TO GET INVENTORY DATA");
        System.out.println("ENTER 4 TO RESET THE INVENTORY");
        System.out.println("ENTER 5 TO EXIT");
    }

    void checkItemAvailability(String ItemName, int quantity) {
        selectedItemName = ItemName;
        AtomicBoolean itemSelected = new AtomicBoolean(false);
        itemInventory.itemList.forEach(item -> {
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                if (item.count >= quantity) {
                    availabilityMessage = "ITEM AVAILABLE";
                    System.out.println("ITEM AVAILABLE");
                    System.out.println("ITEM PRICE: " + item.cost);
                    selectedItemCost = item.cost * quantity;
                    itemSelected.set(true);
                }
            }
        });
        if (!itemSelected.get()) {
            availabilityMessage = "ITEM NOT AVAILABLE. PLEASE SELECT AN AVAILABLE ITEM";
            System.out.println("ITEM NOT AVAILABLE. PLEASE SELECT AN AVAILABLE ITEM");
        }
    }

    private void isPaidEnough(int PENNY , int NICKEL, int DIME,int QUARTER) {
        do {
            if (insertedAmount >= selectedItemCost) {
                paymentMessage = "SUFFICIENT MONEY PROVIDED";
                System.out.println("SUFFICIENT MONEY PROVIDED");
                break;
            } else {
                System.out.println("ITEM PRICE: " + selectedItemCost);
                paymentMessage = "INSERT THE CORRECT AMOUNT OF MONEY";
                System.out.println("INSERT THE CORRECT AMOUNT OF MONEY");
                break;
            }
        }while(insertedAmount == selectedItemCost);

    }

    private void isPaidEnoughMoney(int PENNY , int NICKEL, int DIME,int QUARTER) {
        if (insertedAmount < selectedItemCost) {
            System.out.println("ITEM PRICE: " + selectedItemCost);
            paymentMessage = "INSERT THE CORRECT AMOUNT OF MONEY";
            System.out.println("INSERT THE CORRECT AMOUNT OF MONEY");
        }
        else{
            paymentMessage = "SUFFICIANT MONEY PROVIDED";
            System.out.println("SUFFICIANT MONEY PROVIDED");
        }
    }

}
