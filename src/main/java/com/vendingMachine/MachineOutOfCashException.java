package com.vendingMachine;


// Customised exception to handle machine out of cash scenario
public class MachineOutOfCashException extends Exception {
    private String message;

    public MachineOutOfCashException(String message) {
        super(message);
    }

    public String getCode() {
        return message;
    }

    public void setCode(String code) {
        this.message = message;
    }
}
