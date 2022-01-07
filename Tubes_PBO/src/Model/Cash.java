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
public class Cash extends Payment implements Tax{
    private float cash;

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }
    
    @Override
    public double includeTax(){
        return 0;
    }
    @Override
    public void info(){
        
    }
}
