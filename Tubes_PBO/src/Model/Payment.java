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
    private String jenis_bayar;

    public Payment(Double amount) {
        this.amount = amount;
    }
    public void setJenisBayar(String jenis_bayar) {
        this.jenis_bayar = jenis_bayar;
    }
    public String getJenisBayar(){
        return this.jenis_bayar;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }
    public double calculateTotalPrice(){
        return this.amount;
    }
    public abstract void info();
}
