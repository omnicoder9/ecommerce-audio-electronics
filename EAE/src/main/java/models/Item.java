package models;

import java.math.BigDecimal;

public class Item {
    int itemID;
    String itemName;
    String itemSpecs;
    int numInStock;
    String itemPrice;
    //BigDecimal double price;


    public Item(int itemID, String itemName, String itemSpecs, int numInStock, String itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemSpecs = itemSpecs;
        this.numInStock = numInStock;
        this.itemPrice = itemPrice;
    }

    public Item(String itemName, String itemSpecs, int numInStock, String itemPrice) {
        this.itemName = itemName;
        this.itemSpecs = itemSpecs;
        this.numInStock = numInStock;
        this.itemPrice = itemPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpecs() {
        return itemSpecs;
    }

    public void setItemSpecs(String itemSpecs) {
        this.itemSpecs = itemSpecs;
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }
}
