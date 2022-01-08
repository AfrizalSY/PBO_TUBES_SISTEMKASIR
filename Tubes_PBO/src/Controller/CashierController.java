/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;

/**
 *
 * @author krobus
 */
public class CashierController {

    private Cashier cashier;

    public CashierController() {
        this.cashier = new Cashier("akbul", "vinzmetal", "password");
    }

    public Order createNewOrder(int tableNo) {
        return this.cashier.createOrder(tableNo);
    }

    public void addItem(Item item, int qty, Order order) {
        this.cashier.addItem(item, qty, order);
    }

    public float calculateOrderPrice(Order order) {
        return order.getTotalPrice();
    }
}
