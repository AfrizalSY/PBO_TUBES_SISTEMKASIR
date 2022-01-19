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
public class Beverage extends Item {

    private int volume;

    public Beverage(int item_id,String name, double price,String jenis, int volume) {
        super(item_id,name, price,jenis);
        this.volume = volume;
    }
    public Beverage(String name, double price,String jenis, int volume) {
        super(name, price,jenis);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    public void getItem() {
        System.out.println("Item info :");
        System.out.println("\tName \t: " + this.getName());
        System.out.println("\tWeight \t: " + this.getPrice());
        System.out.println("\tVolume \t: " + this.getVolume());
    }

}
