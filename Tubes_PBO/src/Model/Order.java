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
        return totalPrice;
    }

//    public void setTotalPrice(float totalPrice) {
//        this.totalPrice = totalPrice;
//    }
    
    public OrderItem calculateTotalPrice(){
//        for(OrderItem n :)
    }
    public void getTotalItem(){
        
    }
}
