package com.vendingMachine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test10AmountNotEnoughForPurchase {

    VendingMachineImpl vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
    }

    // Inserted amount is not enough for purchase
    @Test
    public void verifyAmountNotEnoughForPurchase() {
        //Check values before purchase
        Assert.assertEquals("Coin Inventory balance should match", 18.45, vendingMachine.coinInventory.inventoryBalance,0);
        Assert.assertEquals("Coke quantity", 25, vendingMachine.itemInventory.itemList.get(0).count);
        Assert.assertEquals("Pepsi quantity", 35, vendingMachine.itemInventory.itemList.get(1).count);
        Assert.assertEquals("Soda quantity", 45, vendingMachine.itemInventory.itemList.get(2).count);

        // Purchase coke
        vendingMachine.buyAnItemNoEnoughMoney("Coke",1,0,0,0,0);

        //Check values after purchase
        //Check values after top up
        Assert.assertEquals("verify payment message", "INSERT THE CORRECT AMOUNT OF MONEY", vendingMachine.paymentMessage);
    }

    @After
    public void tearDown() {
        vendingMachine.reset();
}
}
