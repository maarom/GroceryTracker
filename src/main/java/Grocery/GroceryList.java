package Grocery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class GroceryList {
    //Fields
    private Connection conn;


    public GroceryList(Connection conn) {
        this.conn = conn;
        //Default items in the list
    }

    //Methods
    public void addGroceryItem(String item) {
        if (checkIfValid(item)){
            try {
                Statement stm = conn.createStatement();
                stm.executeUpdate("insert into grocery" + "(name)" + "values('" + item + "')" );
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Item is not valid");
        }
    }

    public void printGroceryList() {

        ArrayList<String> groceryList = getItems();

        System.out.println("You have: " + groceryList.size() + " items in grocery list");
        for (int i = 0; i < groceryList.size(); i++) {
            System.out.println((i+1) + ". " + groceryList.get(i));
        }
    }

    public void removeGroceryItem(String item){
        int id = findItem(item);

        if (id >= 0) {
            try {
                Statement stm = conn.createStatement();
                stm.executeUpdate("delete from grocery where id = " + id );

            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(item + " Deleted");
        }else {
            System.out.println("Item is not in the list");
        }
    }

    public void storeItems(String item){
        if (checkIfValid(item)){
            try {
                Statement stm = conn.createStatement();
                stm.executeUpdate("INSERT INTO grocery (name) VALUES(" + item + ") ON DUPLICATE KEY UPDATE + stock = stock + 1");
            }catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("Item is not valid");
        }
    }

    public void addGroceryPrice(String item, String newPrice) {

        if (checkIfValid(item)){
            try {
                Statement stm = conn.createStatement();
                stm.executeUpdate("UPDATE grocery SET price = '" + newPrice +  "' WHERE name = '" + item + "'");
            }catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("Item is not valid");
        }
    }

    public void modifyGroceryItem(String item, String newItem){

        if (checkIfValid(newItem)){
            int id = findItem(item);
            try {
                Statement stm = conn.createStatement();
               int result = stm.executeUpdate("update grocery set name = '" + newItem + "' where id = " + id);
               if (result == 0){
                   System.out.println("Item " + id + " not found");
               }
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            System.out.println("Item name is not valid");
        }
    }

    public void deleteAllItems(){
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("delete from grocery");

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("All items in the list are deleted");
    }

    public boolean searchItem(String searchItem){
        if (findItem(searchItem) >= 0){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<String> getItems(){

        ArrayList<String> result = new ArrayList<>();

    try {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("select name from grocery");
        while (rs.next()){
            result.add(rs.getString("name"));
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
    }

    private int findItem(String searchItem){
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select id from grocery where name = '" + searchItem + "' ");
            rs.next();
            return rs.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    private boolean checkIfValid(String input){
        return Pattern.matches("[a-zA-Z ]+[0-9 ]*[a-zA-Z ]*", input);
    }


    // Todo: make categories for items, store how many items is there and make prices for items
}
