/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author WIBU
 */
public class Order {

    private int orderID;
    private int tableNumber;
    private ArrayList<OrderItem> listItem;
    private float totalPrice;

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.listItem = new ArrayList<>();
    }
    
    public void addOrderItem(Item item, int qty){
        OrderItem i = new OrderItem(qty, item);
        this.listItem.add(i);
    }
    
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

    public float getTotalPrice() {
        float total = 0f;
        for (OrderItem orderItem : listItem) {
            total += orderItem.getTotalPrice();
        }
        return total;
    }

//    public void setTotalPrice(float totalPrice) {
//        this.totalPrice = totalPrice;
//    }
    public float calculateTotalPrice() {
        return  0f;
    }

    public void getTotalItem() {

    }
}
