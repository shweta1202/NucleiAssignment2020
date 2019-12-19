package assignment_4;

import java.sql.Connection;
import java.util.Queue;
import java.util.LinkedList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import assignment_4.constants.ItemType;

public class DatabaseConnection {
    Connection con;
    String sql;
    Statement stmt;
    ResultSet rs;
    int sleepTimeForThread = 1000;
    boolean isDataPresent = true;

    Queue<Item> items = new LinkedList<>();
    List<Item> calculatedTaxList = new ArrayList<>();

    public void fetchData() throws InterruptedException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false", "root", "nuclei@123");
            System.out.println("Connection Established!!");
            this.sql = "Select * from itemDetails";
            this.stmt = con.createStatement();
            this.rs = this.stmt.executeQuery(this.sql);
            synchronized (this) {

                while ((this.rs).next()) {

                    Item item = new Item();
                    item.setId((this.rs).getInt("item_id"));
                    item.setItemName((this.rs).getString("item_name"));
                    item.setItemPrice((this.rs).getDouble("item_price"));
                    item.setItemType((this.rs).getString("item_type"));
                    item.setFinalPrice((this.rs).getDouble("final_price"));
                    items.add(item);
                    notify();
                    Thread.sleep(sleepTimeForThread);
                    //System.out.println(item);
                }

            }

            isDataPresent = false;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void calculateTax(Item item) {

        ItemType itemType;
        itemType = ItemType.valueOf(item.getItemType().toUpperCase());
        double tax;
        switch (itemType) {
            case RAW:
                tax = item.getItemPrice() * (12.5 / 100);
                item.setFinalPrice(tax + item.getItemPrice());
                break;
            case MANUFACTURED:
                double taxInitial1;
                taxInitial1 = item.getItemPrice() * 12.5 / 100;
                tax = taxInitial1 + (taxInitial1 + item.getItemPrice()) * 2 / 100;
                item.setFinalPrice(tax + item.getItemPrice());
                break;
            case IMPORTED:
                double taxInitial;
                taxInitial = item.getItemPrice() * 10 / 100;
                if (taxInitial + item.getItemPrice() <= 100)
                    tax = taxInitial + 5;
                else if (taxInitial + item.getItemPrice() <= 200)
                    tax = taxInitial + 10;
                else
                    tax = taxInitial + (taxInitial + item.getItemPrice()) * 5 / 100;
                item.setFinalPrice(tax + item.getItemPrice());
                break;
        }

    }

    public void calculateAndDisplayoperation() throws InterruptedException {
        Thread.sleep(sleepTimeForThread);

        //while (isDataPresent) {
            while (!items.isEmpty()) {
                System.out.println("Hello");
                synchronized (this){
                    while(items.size()==0){
                        wait();
                    }
                }
                Item item = items.poll();
                if(item!=null) {
                    System.out.println("Inside display");
                    calculateTax(item);
                    calculatedTaxList.add(item);
                    display(item);
                }
                //notify();
                Thread.sleep(sleepTimeForThread);

            }
        //}
        System.out.println("Hello1");
    }

    public void display(Item item) {
        System.out.println(item);
    }

    public void closeConnection() {
        try {
            con.close();
            System.out.println("Connection Closed!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}