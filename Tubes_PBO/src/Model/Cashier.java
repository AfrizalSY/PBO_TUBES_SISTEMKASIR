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
public class Cashier implements CashierInterface{
    private String name;
    private String username;
    private String password;
    private ArrayList<Order> listOrder;
    private ArrayList<Payment> listPayment;
    
    public Cashier(String name, String user , String pass) {
        this.name = name;
        this.username = user;
        this.password = pass;
        this.listOrder = new ArrayList<>();
        this.listPayment = new ArrayList<>();        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public void getItem(Item n){
        
    }
    public Order createOrder(int tableNo){
        return new Order(tableNo);        
    }
    @Override
    public void addItem(Item n) {
        
    }

    @Override
    public void addItem(Item n,int qty, Order o) {
        o.addOrderItem(n, qty);
    }

    @Override
    public void editItem(Item n) {

    }

    @Override
    public void editItem(Item n, Order o) {

    }

    @Override
    public void deleteItem(Item n) {

    }

    @Override
    public void deleteItem(Item n, Order o) {

    }
    
    public void showAllPayment(Payment p){
        
    }
    
    public void showAllOrder(){
        
    }
}
