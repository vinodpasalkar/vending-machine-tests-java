package com.vendingMachine;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.vendingMachine.VendingMachineImpl.sc;

enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int denomination;


    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}

// Class which maintains and manages the coin cash inventory
public class CoinInventory {
    private final Map<Coin, Integer> inventory = new HashMap<>();
    double inventoryBalance;
    double insertedAmount = 0;
    double changeAmount = 0 ;
    DecimalFormat df = new DecimalFormat("#.##");

    public void initialize() {
        for(Coin coin: Coin.values()){
            add(coin, 45);
        }
        inventoryBalance = Double.valueOf(df.format(inventoryBalance));
        System.out.println("INVENTORY BALANCE: " + inventoryBalance);
    }

    public double addCoins(int PENNY  , int NICKEL, int DIME,int QUARTER) {

        //Commented below line as the hard coded tests can not accept run time inputs
        //Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of 25 pence coins: ");
        //Commented below line as the hard coded tests can not accept run time inputs
        //insertedAmount += add(Coin.QUARTER, sc.nextInt());
        insertedAmount += add(Coin.QUARTER, QUARTER);
        System.out.print("\nInserted number of 25 pence coins: "+QUARTER);

        System.out.print("\nEnter the number of 10 pence coins: ");
        //Commented below line as the hard coded tests can not accept run time inputs
        //insertedAmount += add(Coin.DIME, sc.nextInt());
        insertedAmount += add(Coin.DIME, DIME);
        System.out.print("\nInserted number of 10 pence coins: "+DIME);

        System.out.print("\nEnter the number of 5 pence coins: ");
        //Commented below line as the hard coded tests can not accept run time inputs
        //insertedAmount += add(Coin.NICKEL, sc.nextInt());
        insertedAmount += add(Coin.NICKEL, NICKEL);
        System.out.print("\nInserted number of 5 pence coins: "+NICKEL);

        System.out.print("\nEnter the number of 1 penny coins: ");
        //Commented below line as the hard coded tests can not accept run time inputs
        //insertedAmount += add(Coin.PENNY, sc.nextInt());
        insertedAmount += add(Coin.PENNY, PENNY);
        System.out.print("\nInserted number of 1 penny coins: "+PENNY);

        inventoryBalance = Double.valueOf(df.format(inventoryBalance));
        System.out.println("\nINVENTORY BALANCE: " + inventoryBalance);

        return insertedAmount;

    }

    public double add(Coin item, int noOfCoins) {
        if (inventory.containsKey(item)) {
            int count = inventory.get(item);
            inventory.put(item, count + noOfCoins);
        } else {
            inventory.put(item, noOfCoins);
        }

        double insertedAmount = (item.getDenomination() * noOfCoins) / 100.0;
        inventoryBalance += insertedAmount;

        return insertedAmount;
    }

    //TODO - If the cash inventory doesn't have enough change to dispense, this goes in infinite loop. To be fixed.
    public double dispenseCoins(double balanceToBePaid) throws MachineOutOfCashException {
        System.out.println("TOTAL CHANGE TO DISPENSE: " + Double.valueOf(df.format(balanceToBePaid)));
        changeAmount = balanceToBePaid;
    try{
        if (balanceToBePaid > 0) {
            while (balanceToBePaid > 0) {
                for (Coin coin : inventory.keySet()) {
                    if (this.inventory.get(coin) > 0) {
                        if (balanceToBePaid >= (Double.valueOf(coin.getDenomination()) / 100.0) && inventory.get(coin) >= 1) {
                            balanceToBePaid = deduct(balanceToBePaid, coin);
                        }
                    } else {
                        System.out.println("Machine out of cash for: " + coin.getDenomination() + "P");
                        throw new MachineOutOfCashException("Machine out of cash for: " + coin.getDenomination() + "P");
                    }
                }
            }
        }
    }
    finally {
        System.out.println("BALANCE TO BE PAID: " + balanceToBePaid);

    }
        return balanceToBePaid;
    }

    private double deduct(double balance, Coin coin) {

            double valueToDeduct = Double.valueOf(coin.getDenomination()) / 100;
            balance = Double.valueOf(df.format(balance - valueToDeduct));
            //if (this.inventory.get(coin) == null) {
                System.out.println("COIN DISPENSED: " + coin.getDenomination() + "P");
            //} else {
                //System.out.println("Machine out of cash for: " + coin.getDenomination() + "P");
            //}

            this.inventory.put(coin, this.inventory.get(coin) - 1);
            this.inventoryBalance = Double.valueOf(df.format(this.inventoryBalance - valueToDeduct));


        return balance;
    }

    public void reset() {
        inventory.clear();
        inventoryBalance = 0.00;
    }
}