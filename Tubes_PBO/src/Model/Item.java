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
public class Item {
    private int item_id;
    private String name;
    private double price;
    private String jenis;
    public Item(int item_id, String name, double price, String jenis) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.jenis = jenis;
    }
    public Item(String name, double price, String jenis) {
        this.name = name;
        this.price = price;
        this.jenis = jenis;
    }
    public int getItem_Id(){
        return item_id;
    }
    public void setItem_Id(int item_id){
        this.item_id = item_id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    
}
