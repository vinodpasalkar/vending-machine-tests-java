package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test4PurchaseCokeAndCancelOneQuantity {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Complete coke purchase and cancel the transaction and validate the refund amount
    @Test
    public void verifyCompletePurchaseCokeAndCancel() throws MachineOutOfCashException {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Purchase coke
        vendingMachine.buyAnItem("Coke",1,1,2,3,4);

        //Check values after purchase
        Assert.assertEquals(" Coin Inventory balance should match", 19.6, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Inserted amount should match", 1.41, vendingMachine.coinInventory.insertedAmount,0.01);
        Assert.assertEquals("Change to dispense amount should be correct", 0.26, vendingMachine.coinInventory.changeAmount,0.01);
        Assert.assertEquals("Coke quantity should decrease by 1", 24, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should not change", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should not change", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Cancel the request
        vendingMachine.cancelRequestAndRefund("Coke",1,1,2,3,4);

        //Check values after cancel
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should increase by 1", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should not change", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should not change", 45, vendingMachine.itemInventory.itemList.get(2).count);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
