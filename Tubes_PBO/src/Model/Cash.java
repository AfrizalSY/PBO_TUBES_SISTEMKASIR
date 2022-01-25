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
public class Cash extends Payment implements Tax {

    private double cash;

    public Cash(double amount, double cash) {
        super(amount);
        this.cash = cash;
    }
    public Cash(double amount) {
        super(amount);
    }
    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public double includeTax() {
        double total = 0;
        total = calculateTotalPrice()+(calculateTotalPrice() * 10/100);
        return total;
    }

    @Override
    public void info() {

    }
}
