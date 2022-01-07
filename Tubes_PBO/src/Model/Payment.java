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
    private float amount;
    private Order order;
    
    public Order calculateTotalPrice(){
        return ;
    }
    public abstract void info();
}
