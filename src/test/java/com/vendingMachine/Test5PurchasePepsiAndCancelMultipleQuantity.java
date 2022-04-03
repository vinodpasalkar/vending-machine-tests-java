package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test5PurchasePepsiAndCancelMultipleQuantity {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Complete pepsi purchase and cancel the transaction and validate the refund amount
    @Test
    public void verifyCompletePurchasePepsiAndCancel() throws MachineOutOfCashException {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should match", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should match", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should match", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Purchase pepsi
        vendingMachine.buyAnItem("Pepsi",2,1,2,3,10);

        //Check values after purchase
        Assert.assertEquals("Coin Inventory balance should match", 21.05, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Inserted amount should be correct", 2.90, vendingMachine.coinInventory.insertedAmount,0.01);
        Assert.assertEquals("Change to dispense amount should be correct", 0.31, vendingMachine.coinInventory.changeAmount,0.01);
        Assert.assertEquals("Coke quantity should not change", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should be decrease by 2", 33, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should not change", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Cancel the request
        vendingMachine.cancelRequestAndRefund("Pepsi",2,1,2,3,4);

        //Check values after cancel
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should not change", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should be increase by 2", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should not change", 45, vendingMachine.itemInventory.itemList.get(2).count);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
