/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author WIBU
 */
public abstract class Payment {
    private double amount;
    private Order order;

    public Payment(float amount, Order order) {
        this.amount = amount;
        this.order = order;
    }
    public double calculateTotalPrice(){
        this.amount = this.order.getTotalPrice();
        return this.amount;
    }
    public abstract void info();
}
