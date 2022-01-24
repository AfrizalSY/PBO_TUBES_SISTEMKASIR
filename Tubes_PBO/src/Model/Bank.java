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

    public Bank(Double amount) {
        super(amount);
    }  

    public Bank(String bankName, String bankID, Double amount) {
        super(amount);
        this.bankName = bankName;
    }

  
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    @Override
    public double includeTax(){
        double total = 0;
        total = calculateTotalPrice()+(calculateTotalPrice() * 10/100);
        return total;
    }
    @Override
    public void info(){
        
    }
}
