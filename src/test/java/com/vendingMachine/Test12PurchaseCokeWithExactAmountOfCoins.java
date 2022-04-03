package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test12PurchaseCokeWithExactAmountOfCoins {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Inserted amount is exact, so no amount ( change ) to be returned
    @Test
    public void verifyCompletePurchaseCokeWithExactCoins() throws MachineOutOfCashException {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should match", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should match", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should match", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Purchase coke
        vendingMachine.buyAnItem("Coke",1,0,1,1,4);

        //Check values after purchase
        Assert.assertEquals("Coin Inventory balance should increase", 19.6, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Inserted amount should match", 1.15, vendingMachine.coinInventory.insertedAmount,0.01);
        Assert.assertEquals("Change to dispense amount should be correct", 0.0, vendingMachine.coinInventory.changeAmount,0.01);
        Assert.assertEquals("Coke quantity should decrease after purchase", 24, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should not change", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should not change", 45, vendingMachine.itemInventory.itemList.get(2).count);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
