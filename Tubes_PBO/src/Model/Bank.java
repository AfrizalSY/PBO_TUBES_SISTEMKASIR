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
public class Bank extends Payment implements Tax{
    
    private String bankName;
    private String bankID;
  public Bank(float amount, Order order,String bankName, String bankID) {
        super(amount, order);
        this.bankName = bankName;
        this.bankID = bankID;
    }
  
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }
    
    @Override
    public double includeTax(){
        double total = 0;
        total = calculateTotalPrice()+(calculateTotalPrice() * (10/100));
        return total;
    }
    @Override
    public void info(){
        
    }
}
