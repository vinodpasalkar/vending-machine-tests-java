package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Test6PurchaseShouldbeLessThan5Secs {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Complete soda purchase and cancel the transaction and validate the refund amount
    @Test(timeout = 6)
    public void PurchaseShouldbeLessThan5Secs() throws InterruptedException, MachineOutOfCashException {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should match", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should match", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should match", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Purchase soda
        vendingMachine.buyAnItem("Soda",1,1,2,3,4);

        //Check values after purchase
        Assert.assertEquals("Coin Inventory balance should match", 19.25, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Inserted amount", 1.41, vendingMachine.coinInventory.insertedAmount,0.01);
        Assert.assertEquals("Change to dispense", 0.61, vendingMachine.coinInventory.changeAmount,0.01);
        Assert.assertEquals("Coke quantity should not change", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should not change", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should be decrease by 1", 44, vendingMachine.itemInventory.itemList.get(2).count);


    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
