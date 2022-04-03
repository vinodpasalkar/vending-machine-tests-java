package com.vendingMachine;

public interface VendingMachineAPI {
    public void initialize();
    public void buyAnItem(String item,int quantity,int PENNY , int NICKEL, int DIME,int QUARTER) throws MachineOutOfCashException;
    public void cancelRequestAndRefund(String item,int quantity,int PENNY , int NICKEL, int DIME,int QUARTER) throws MachineOutOfCashException;
    public void getInventoryData();
    public void reset();
}
