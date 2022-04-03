package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Test7EnteredInvalidItem {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Invalid item entered
    @Test
    public void verifyForInvalidItem() {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Add items
        vendingMachine.checkItemAvailability("Fanta",1);

        //Check values after top up
        Assert.assertEquals("verify unavailability message", "ITEM NOT AVAILABLE. PLEASE SELECT AN AVAILABLE ITEM", vendingMachine.availabilityMessage);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
