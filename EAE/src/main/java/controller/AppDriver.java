package controller;

import models.Order;
import models.User;
import repositories.UserRepo;
import services.OrderRequestException;
import services.UserServices;

import java.util.ArrayList;
import java.util.Scanner;

public class AppDriver {
    public static Scanner scanner = new Scanner(System.in);

    private static UserServices userServices = new UserServices();
    private static UserRepo userRepo;

    //UserRepo userRepo = new UserRepo();

    public static int mainMenu(){
        //prints main menu

        int[] ints = {1, 2, 3, 4};
        while(true) {

            System.out.println("Welcome to the Online Audio Electronics Shop!\nPlease choose an option below.");
            System.out.println("1. Login\n2. Create Account \n3. View Merchandise \n4. Quit");

            int input = scanner.nextInt();
            //int[] ints = {1, 2, 3, 4};
            for (int i : ints) {
                if (input == i) {
                    return input;
                }
            }
            System.out.println("Please enter either 1, 2, 3, or 4 to choose an option.");
        }



        //validate input: must be 1, 2, or 3.
        /*boolean x = true;
        while(x) {
            System.out.println("Welcome to the Online Audio Electronics Shop!\nPlease choose an option below.");
            System.out.println("1. Login\n2. Create Account \n3. View Merchandise \n4. Quit");
            int input = scanner.nextInt();
            if (input ==1 || input ==2){
                //x = false;
                return input;
            }
            if (input==3 || input==4){
                //x = false;
                return input;
            }
            if (input != 1 && input != 2) {
                if (input != 3 && input != 4) {
                    System.out.println("Please enter either 1, 2, 3, or 4 to choose an option.");
                    x = true;
                    input = scanner.nextInt();
                }
            }
        }*/
        //return 0;
    }

    public void loginMenu(){
        //prints login menu
    }

    public void customerMenu(){

    }

    public void employeeMenu(){

    }

    public void managerMenu(){

    }


    public static void main(String[] args) {
/*
        System.out.println("Welcome to the Online Audio Electronics Shop!\nPlease choose an option below.");
        System.out.println("1. Login\n2. Create Account \n3. View Merchandise \n4. Quit");

        int input = scanner.nextInt();
        //validate input: must be 1, 2, or 3.
        boolean x = true;
        do {
            if (input ==1 || input ==2){
                if (input==3 || input==4){
                    x = false;
                }
            }
            if (input != 1 && input != 2) {
                if (input != 3 && input != 4) {
                    System.out.println("Please enter either 1, 2, 3, or 4 to choose an option.");
                    x = true;
                    input = scanner.nextInt();
                }
            }
        }while(x);
*/
        //boolean y = true;
        boolean a = true;
        while(a) {
            int input = AppDriver.mainMenu();

            switch (input) {

                case 1: {
                    // calling this once to clear anything in the input stream
                    scanner.nextLine();

                    System.out.println("Please enter your username: ");
                    String username = scanner.nextLine();

                    System.out.println("Please enter your password: ");
                    String password = scanner.nextLine();//figure out how to get *** for the password input

                    // We need a login service to check if username and password match credentials stored in the database
                    User signInResponse = userServices.login(username, password);

                    if (signInResponse != null) {
                        System.out.println("Successfully logged in.");
                        System.out.println(signInResponse.getType());//prints Customer


                        //boolean b = true;
                        while(true) {
                            //int input2 = -1;
                            int input2 = userServices.displayUserMenu(signInResponse);

                            if (input2 == 1) {
                                break;//return to main/start menu
                            }
                            if (input2 == 2) {
                                //view profile
                                int input3 = userServices.viewProfile(signInResponse);
                                if (input3 == 4) {//logout and quit
                                    //b = false;
                                    break;
                                    //a = false;
                                }

                                /*if (input3 == 1){//do nothing
                                    break;
                                }*/
                                if (input3 == 2){
                                    //view order history
                                    userServices.displayUserOrderHistory(signInResponse);

                                    System.out.println("\n");

                                }
                                if (input3 == 3){
                                    //view shopping cart
                                }
                            }
                            if (input2 == 3){
                                //view merch screen
                                try {
                                    userServices.printMerch(signInResponse);
                                } catch (OrderRequestException e){
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            if (input2 == 4){
                                System.exit(0);
                                //break;//logout and quit
                            }
                            if (input2 == 5){
                                //employee is updating inventory
                                userServices.updateInventory();
                            }
                        }
                        //User u = userRepo.getByUsername(username);//null pointer exception
                        //y = false;
                    } else {
                        System.out.println("Credentials do not match. ");
                        //y = true;
                    }
                    break;
                }
                case 2: {
                    //create new account
                    //newAccount()
                    userServices.newAccount();
                    break;
                }
                case 3: {
                    //print merchandise
                    userServices.printMerch();
                    break;

                }
                case 4: {
                    a = false;

                    break;
                }
            }
            //break;
        }


        scanner.close();
        System.out.println("Program terminated.");

    }
}

