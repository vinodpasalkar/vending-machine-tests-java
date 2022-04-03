package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test9TopUpVendingMachine {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Top up the vending machine ( add quantity for products)
    @Test
    public void verifyTopUpOfVendingMachine() {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Add items
        vendingMachine.topUpItem("Coke",10);
        vendingMachine.topUpItem("Pepsi",5);
        vendingMachine.topUpItem("Soda",5);
        vendingMachine.getInventoryData();

        //Check values after top up
        Assert.assertEquals("Coke quantity", 35, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 40, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 50, vendingMachine.itemInventory.itemList.get(2).count);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
