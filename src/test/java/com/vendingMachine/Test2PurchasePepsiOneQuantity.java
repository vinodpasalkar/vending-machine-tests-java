package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test2PurchasePepsiOneQuantity {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Complete pepsi purchase for 1 quantity and return remaining amount when more coins are inserted than item price
    @Test
    public void verifyCompletePurchasePepsiOneQuantity() throws MachineOutOfCashException {
        //Check values before purchase
        Assert.assertEquals(" Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity should match", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should match", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should match", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Purchase Pepsi
        vendingMachine.buyAnItem("Pepsi",1,1,2,3,4);

        //Check values after purchase
        Assert.assertEquals("Coin Inventory balance should match", 19.75, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Inserted amount ", 1.41, vendingMachine.coinInventory.insertedAmount,0.01);
        Assert.assertEquals("Change to dispense amount should be correct", 0.11, vendingMachine.coinInventory.changeAmount,0.01);
        Assert.assertEquals("Coke quantity should not change", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity should decrease after purchase", 34, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity should not change", 45, vendingMachine.itemInventory.itemList.get(2).count);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
