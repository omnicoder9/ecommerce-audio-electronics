package models;

public class Order {
    int orderID;
    int userID;
    int itemID;
    int quantity;
    String timestamp;
    String status;

    public Order(int orderID, int userID, int itemID, int quantity, String timestamp, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Order(int userID, int itemID, int quantity, String timestamp, String status) {
        this.userID = userID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
