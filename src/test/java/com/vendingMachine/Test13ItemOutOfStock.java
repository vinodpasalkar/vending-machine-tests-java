package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test13ItemOutOfStock {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Item out of stock
    @Test
    public void verifyItemOutOfStock() {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Add items
        vendingMachine.stockFinishedFor("Coke");

        //Check values after top up
        Assert.assertEquals("verify stock message", "ITEM OUT OF STOCK", vendingMachine.stockMessage);
    }

    @After

    public void tearDown() {
        vendingMachine.reset();
}
}
