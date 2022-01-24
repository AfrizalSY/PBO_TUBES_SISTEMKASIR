/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WIBU
 */
public class Order {

    private int orderID;
    private int tableNumber;
    private ArrayList<OrderItem> listItem;
    private Database db = new Database();

    public Order() {

    }

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.listItem = new ArrayList<>();

    }

    // public void addOrderItem(Item item, int qty) {
    //     OrderItem i = new OrderItem(qty, item);
    //     this.listItem.add(i);
    // }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public ArrayList<OrderItem> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<OrderItem> listItem) {
        this.listItem = listItem;
    }

    public double getTotalPrice() {
        double total = 0f;
        for (OrderItem orderItem : listItem) {
            total += orderItem.setTotalPrice();
        }
        return total;
    }
    
    public void updateTotalPrice(int idOrder, double price) throws SQLException{
        db.connectDB();
        String sql = "UPDATE order_table SET total_pembayaran = '%s' WHERE id_order = %d";
        sql = String.format(sql, price, idOrder);
        System.out.println(sql);
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    public void getTotalItem() {
        int total = 0;
        for (OrderItem orderItem : listItem) {
            System.out.println("Item: " + orderItem.getItem().getName() + " \tQuantity:" + orderItem.getQuantity());
            total += orderItem.getQuantity();
        }
        System.out.println("Total Item: " + total);
    }
}
