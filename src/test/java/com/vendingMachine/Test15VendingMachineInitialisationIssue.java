package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test15VendingMachineInitialisationIssue {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
    }

    // Complete coke purchase and return remaining amount when more coins are inserted than item price
    @Test
    public void verifyVendingMachineInitialisationIssue() {
        //Check values
        Assert.assertEquals("Coin Inventory balance should match", 0.0, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Item Inventory balance should match", 0, vendingMachine.itemInventory.itemList.size(),0);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
