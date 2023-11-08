package services;

import models.Item;
import models.Order;
import models.User;
import repositories.ItemRepo;
import repositories.OrderRepo;
import repositories.UserRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserServices {
    //login
    //buy products
    //edit inventory (employees only)

    public static Scanner scanner = new Scanner(System.in);


    UserRepo userRepo = new UserRepo();
    ItemRepo itemRepo = new ItemRepo();
    OrderRepo orderRepo = new OrderRepo();

    public User login(String username, String password) {


        User u = userRepo.getByUsername(username);
        //return u;

        // check to make sure User object is not null
        if (u != null) {
            // now check to make sure it matches
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                return u;
            }
        }
        return null;
    }
    //display user menu, different for each type of user
    public int displayUserMenu(User u){
        //User uN = userRepo.getById(u.getAccountID());
        //System.out.println(uN.getType());
        if (u.getType().equals("Customer")) {

            int ii = 0;
            while (ii < 10) {
                System.out.println("Welcome customer, please choose an option below.");
                System.out.println("1. Log Out\n2. View Profile \n3. View Merchandise \n4. Log Out and Quit");
                int input = scanner.nextInt();
                int[] ints = {1,2,3,4};
                for (int i: ints){
                    if (input == i){
                        //System.out.println("valid input");
                        ii = 10;
                        return input;

                    }
                }
                ii++;
                System.out.println("Please enter either 1, 2, 3, 4, or 5 to choose an option.");

            }
        }
        if (u.getType().equals("Employee")) {
            boolean x = true;
            while (x) {
                System.out.println("Welcome, please choose an option below.");
                System.out.println("1. Log Out\n2. View Profile \n3. View Merchandise \n4. Log Out and Quit  \n 5. Update/Edit Inventory");
                int input = scanner.nextInt();
                int[] ints = {1,2,3,4,5};
                for (int i: ints){
                    if (input == i){
                        System.out.println("valid input");
                        x = false;
                        return input;
                    }
                }
                System.out.println("Please enter either 1, 2, 3, 4, or 5 to choose an option.");
                /*
                if (input == 1 || input == 2) {
                    //x = false;
                    return input;
                }
                if (input == 3 || input == 4) {
                    //x = false;
                    return input;
                }
                if (input != 1 && input != 2) {
                    if (input != 3 && input != 4) {
                        System.out.println("Please enter either 1, 2, 3, or 4 to choose an option.");
                        //x = true;
                        //input = scanner.nextInt();
                    }
                }*/
            }
        }
        return 0;

    }

    //view profile
    public int viewProfile(User u){
        //System.out.println(u.getType());
        if (u.getType().equals("Customer")) {
            boolean x = true;
            while (x) {
                System.out.println("Profile for " + u.getUsername());
                System.out.println("1. Back\n2. View Order History \n3. View Shopping Cart \n4. Log Out and Quit");
                int input = scanner.nextInt();

                int[] ints = {1,2,3,4};
                for (int i: ints){

                    if (input == i){
                        return input;
                    }
                }
                System.out.println("Please enter either 1, 2, 3, 4, or 5 to choose an option.");

            }
        }
        if (u.getType().equals("Employee")) {
            boolean x = true;
            while (x) {
                System.out.println("Profile for " + u.getUsername());
                System.out.println("1. Back\n2. View Order History \n3. View Shopping Cart \n4. Log Out and Quit");
                int input = scanner.nextInt();
                int[] ints = {1,2,3,4,5};
                for (int i: ints){
                    if (input == i){
                        return input;
                    }
                }
                System.out.println("Please enter either 1, 2, 3, 4, or 5 to choose an option.");
                /*
                if (input == 1 || input == 2) {
                    //x = false;
                    return input;
                }
                if (input == 3 || input == 4) {
                    //x = false;
                    return input;
                }
                if (input != 1 && input != 2) {
                    if (input != 3 && input != 4) {
                        System.out.println("Please enter either 1, 2, 3, or 4 to choose an option.");
                        //x = true;
                        //input = scanner.nextInt();
                    }
                }*/
            }
        }
        return 0;

    }
    public void printMerch(){
        System.out.println("Item ID | Item Name | Item Specifications | Quantity Available | Price per Unit");
        List<Item> listI = itemRepo.getAll();
        for (Item it : listI){
            System.out.println("\n" + it.getItemID() + "|" +
                    it.getItemName() + "|" +
                    it.getItemSpecs() + "|" +
                    it.getNumInStock() + "| $" +
                    it.getItemPrice());
        }
    }
    //buy products
    public void printMerch(User u) throws OrderRequestException {
        //print all items in database
        System.out.println("Item ID | Item Name | Item Specifications | Quantity Available | Price per Unit");
        List<Item> listI = itemRepo.getAll();
        for (Item it : listI){
            System.out.println("\n" + it.getItemID() + "|" +
                    it.getItemName() + "|" +
                    it.getItemSpecs() + "|" +
                    it.getNumInStock() + "| $" +
                    it.getItemPrice());
        }
        System.out.println("\nEnter item ID to order item. Enter multiple values to order multiple items.  " +
                "\nEnter the item ID more than once to get multiple of the same item." +
                "\nEnter 0 to place order.  Enter -1 to quit without placing order.");

            ArrayList<Integer> items = new ArrayList<Integer>();
            int placeOrder = 1;
            boolean c = true;
            while(c) {

                int input = scanner.nextInt();

                if (input == 0 || input == -1){
                    c = false;
                }
                if (input == -1){
                    placeOrder = 0;
                }
                if (input != 0 && input != -1){
                    items.add(input);
                }


            }
            if (placeOrder == 1) {
                System.out.println("Submitting order...");

                for (Integer itemID : items) {
                    //remove 1 from inventory
                    //int itemID = itemID;
                    Item it = itemRepo.getById(itemID);
                    int n = it.getNumInStock();
                    if (n <= 0){
                        throw new OrderRequestException("Insufficient inventory.");
                    }
                    int m = n - 1;
                    it.setNumInStock(m);
                    itemRepo.update(it);
                    //add record to order history
                    Order newOrder = new Order(u.getAccountID(), itemID, 1, "14:30", "Pending");
                    orderRepo.add(newOrder);
                }
                System.out.println("Order has been placed.");
            }
    }

    public void displayUserOrderHistory(User u){
        int userID = u.getAccountID();
        List<Order> userOrders = orderRepo.getByUserId(userID);
        System.out.println("Order ID | Item ID | Item Name | Quantity | Status | Time | Customer Username | Customer ID");
        for(Order o : userOrders){
            int customerID = o.getUserID();
            User customer = userRepo.getById(customerID);
            Item thisItem = itemRepo.getById(o.getItemID());
            System.out.println(o.getOrderID() + " | " +
                    o.getItemID() + " | " +
                    thisItem.getItemName() + " | " +
                    o.getQuantity() + " | " +
                    o.getStatus() + " | " +
                    o.getTimestamp() + " | " +
                    customer.getUsername() + " | " + o.getUserID());
        }

    }
    //update database
    //reduce quantity of item, add transaction to order history

    //edit inventory: add or update 'items' table in database
    public void updateInventory(){
        boolean x = true;
        while (x) {
            System.out.println("Select an option: ");
            System.out.println("1. Back\n2. Add item \n3. Edit item (feature not added yet) \n4. Log Out and Quit");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 2){
                System.out.println("Enter the item name, specifications, quantity, and price separated by commas.");
                String itemIn = scanner.nextLine();
                List<String> itemList = Arrays.asList(itemIn.split(","));
                int quantity = Integer.parseInt(itemList.get(2));
                Item newItem = new Item(itemList.get(0),itemList.get(1),quantity,itemList.get(3));
                itemRepo.add(newItem);

            }
            if (input == 1){
                x = false;
            }
            if (input == 4){
                System.exit(0);
            }
            boolean h = true;
            int[] ints = {1, 2, 3, 4};
            for (int i : ints) {
                if (input == i) {
                    h = false;
                }
            }
            if (h){
                System.out.println("Please enter either 1, 2, 3, or 4 to choose an option.");
            }

        }

    }
    public void newAccount(){

        scanner.nextLine();
        System.out.println("Please enter your username, password, email address, and payment info separated by commas and no extra spaces/whitespace.  \nThen press enter.");



        String itemIn = scanner.nextLine();
        List<String> itemList = Arrays.asList(itemIn.split(","));

        if (itemList.size() == 4) {
            User newUser = new User(itemList.get(0), itemList.get(1), itemList.get(3), itemList.get(2), "Customer");
            userRepo.add(newUser);
        }else{
            System.out.println("Invalid input. Cannot create account.");
        }
    }
}
