package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test8EnteredValidItem {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    //Valid item entered
    @Test
    public void verifyItemPresent() {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Add items
        vendingMachine.checkItemAvailability("Coke",1);

        //Check values after top up
        Assert.assertEquals("verify availability message", "ITEM AVAILABLE", vendingMachine.availabilityMessage);
    }

    @After

    public void tearDown() {
        vendingMachine.reset();
}
}
