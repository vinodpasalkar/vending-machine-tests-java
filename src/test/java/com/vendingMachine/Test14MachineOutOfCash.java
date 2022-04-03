package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

import static com.vendingMachine.Coin.*;
import static org.junit.Assert.assertThrows;

public class Test14MachineOutOfCash {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Complete pepsi purchase and return remaining amount when more coins are inserted than item price
    @Test()
    public void verifyNotEnoughMoney() {
        vendingMachine.coinInventory.reset();
        vendingMachine.coinInventory.add(PENNY,5);
        vendingMachine.coinInventory.add(NICKEL,5);
        vendingMachine.coinInventory.add(DIME,10);
        vendingMachine.coinInventory.add(QUARTER,4);

        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 2.30, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should match", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should match", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should match", 45, vendingMachine.itemInventory.itemList.get(2).count);


        // Purchase Pepsi
        //vendingMachine.buyAnItem("Pepsi",0,0,0,15);

        //Check values after purchase
        Assertions.assertThrows(MachineOutOfCashException.class,() ->vendingMachine.buyAnItem("Pepsi",1,0,0,0,15));
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
