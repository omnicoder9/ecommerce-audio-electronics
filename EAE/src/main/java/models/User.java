package models;

public class User {

    String username;
    String password;
    int accountID;
    String paymentInfo;
    String email;
    String type;

    public User(String username, String password, String paymentInfo, String email) {
        this.username = username;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.email = email;
    }

    public User(String username, String password, String paymentInfo, String email, String type) {
        this.username = username;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.email = email;
        this.type = type;
    }

    public User(int id, String username, String password, String paymentInfo, String email, String type) {
        this.accountID = id;
        this.username = username;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.email = email;
        this.type = type;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
