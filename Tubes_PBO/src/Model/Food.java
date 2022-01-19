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
public class Food extends Item{
    private int weight;

    public Food(int item_id,String name, double price,String jenis, int weight) {
        super(item_id,name, price,jenis);
        this.weight = weight;
    }
    public Food(String name, double price,String jenis, int weight) {
        super(name, price,jenis);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public void getItem() {
        System.out.println("Item info :");
        System.out.println("\tName \t: " + this.getName());
        System.out.println("\tPrice \t: " + this.getPrice());
        System.out.println("\tWeight \t: " + this.getWeight());
    }
}
