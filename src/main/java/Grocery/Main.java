package Grocery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList;

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/java30";
        String user = "root";
        String password = "Jannekallisemme1";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            groceryList = new GroceryList(conn);
            System.out.println("All good");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        boolean quit = false;
        int choice = 0;
        printInstructions();

        while (!quit){
            System.out.println("Enter your choice");

            try {
                choice = scanner.nextInt();
                //buf fix
                scanner.nextLine();
                switch (choice){
                    case 0:
                        //print all options
                        printInstructions();
                        break;
                    case 1:
                        //print grocery list
                        groceryList.printGroceryList();
                        break;
                    case 2:
                        //add item
                        addItem();
                        break;
                    case 3:
                        //modify item
                        modifyItem();
                        break;
                    case 4:
                        //remove item
                        removeItem();
                        break;
                    case 5:
                        //search item
                        searchForItem();
                        break;
                    case 6:
                        //Delete all items
                        groceryList.deleteAllItems();
                        break;
                    case 7:
                        //quit
                        quit = true;
                        break;
                    case 8:
                        addPrice();
                        break;
                    default:
                        System.out.println("Input no valid");
                }
            }catch (InputMismatchException e){
                System.err.println("Wrong input! Integers only!");
                //buf fix
                scanner.nextLine();
            }

        }

    }

    public static void printInstructions(){
        System.out.println("\nPress");
        System.out.println("\t 0 - To print choice options");
        System.out.println("\t 1 - To print the list of grocery items");
        System.out.println("\t 2 - To add an item to the list");
        System.out.println("\t 3 - To modify an item in the list");
        System.out.println("\t 4 - To remove an item from the list");
        System.out.println("\t 5 - To search for an item in the list");
        System.out.println("\t 6 - To delete all item in the list");
        System.out.println("\t 7 - To quit the application");
        System.out.println("\t 8 - To add an item price");

    }

    public static void addItem(){
        System.out.println("Please enter the grocery item:");
        groceryList.addGroceryItem(scanner.nextLine());
    }

    public static void addPrice(){
        System.out.println("Enter item name");
        String item = scanner.nextLine();

        System.out.println("Please enter the grocery item price:");
        String newPrice = scanner.nextLine();
        groceryList.addGroceryPrice(item, newPrice);
    }

    public static void modifyItem(){
        System.out.println("Enter item name");
        String name = scanner.nextLine();

        System.out.println("Enter replacement item");
        String newItem = scanner.nextLine();
        groceryList.modifyGroceryItem(name ,newItem);
    }

    public static void removeItem(){
        System.out.println("Enter item name:");
        groceryList.removeGroceryItem(scanner.nextLine());
    }

    public static void searchForItem(){
        System.out.println("Enter item to search for");
        String searchItem = scanner.nextLine();
        if (groceryList.searchItem(searchItem)){
            System.out.println("Item is in the list");
        }else {
            System.out.println("Item is not found in the list");
        }
    }

}
