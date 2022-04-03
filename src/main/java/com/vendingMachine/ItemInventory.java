package com.vendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Class which maintains and manages the item inventory
public class ItemInventory {
    List<Item> itemList = new ArrayList<>();

    public void initialize() {
        itemList.add(new Item("SKU1","COKE", 25, 1.15));
        itemList.add(new Item("SKU2","PEPSI", 35, 1.30));
        itemList.add(new Item("SKU3","SODA", 45, 0.80));
        itemList.add(new Item("SKU4","DIET COKE", 25, 1.15));
        printItems();
    }

    public void printItems(){
        System.out.println("ITEM_NAME" + "\t|\t" + "ITEM_COST" + "\t|\t" + "ITEM_COUNT");
        for (Item item : itemList) {
            System.out.println(item.name + "\t\t|\t" + item.cost + "\t\t|\t" + item.count);
        }
    }
    public void dispenseItem(String selectedItemName,int quantity) {
        for(int i=0;i<quantity;i++) {
            this.itemList.forEach(item -> {
                if (item.name.equalsIgnoreCase(selectedItemName) && item.count > 0) {
                    item.count--;
                    System.out.println("COLLECT YOUR ITEM NOW");
                } else {
                    System.out.println("ITEM OUT OF STOCK");
                }
            });
        }
    }
    public void addBackItem(String selectedItemName,int quantity){
            for (Item item : itemList) {
                if (item.name.equalsIgnoreCase(selectedItemName)) {
                    item.count = item.count+quantity;
                    System.out.println("REQUEST CANCELLED. ITEM CANNOT BE COLLECTED NOW");
                    return;
                }
            }

    }

    public void addItem(String selectedItemName,int quantity){
        for(Item item: itemList){
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                item.count =item.count + quantity;
                return;
            }
        }
    }

    public void reset() {
        itemList.clear();
    }

    public void stockOverForItem(String selectedItemName) {
        this.itemList.forEach(item -> {
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                item.count = 0;
            }
            });
    }


    public class Item {
        String sku;
        String name;
        int count;
        double cost;

        public Item(String sku,String name, int count, double cost) {
            this.sku=sku;
            this.name = name;
            this.count = count;
            this.cost = cost;
        }
    }
}
