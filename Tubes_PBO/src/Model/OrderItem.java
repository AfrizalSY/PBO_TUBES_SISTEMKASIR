/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import Database.Database;
import java.sql.SQLException;
/**
 *
 * @author WIBU
 */
public class OrderItem {
    private int order_item_id;
    private String item_jenis;
    private int item_id;
    private double price_item;
    private int quantity;
    private String nama_item;
    private Item item;
    private Database db = new Database();

    public OrderItem(){

    }
    public OrderItem(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }
    public OrderItem(int item_id,String jenis ,String nama_item,int quantity,double price_item) {
        this.item_id = item_id;
        this.item_jenis =jenis;
        this.nama_item = nama_item;
        this.quantity = quantity;
        this.price_item = price_item;
    }
    public int getOrderItemId() {
        return order_item_id;
    }

    public void setOrderItemId(int orderItemId) {
        this.order_item_id = orderItemId;
    }

    public String getItem_jenis() {
        return item_jenis;
    }

    public void setItem_jenis(String item_jenis) {
        this.item_jenis = item_jenis;
    }

    public int getItemId() {
        return item_id;
    }
    public void setItemId(Item f) {
        this.item_id = f.getItem_Id();
    }
    public String getNamaItem() {
        return nama_item;
    }
    public void setNamaItem(Item f) {
        this.nama_item= f.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    } 
    
    // Querry untuk mengambil semua item order yang ada berdasarkan order id yang dimasukan
    public ArrayList<OrderItem> getAllOrderItem(int id) throws SQLException{
        db.connectDB();
        ArrayList<OrderItem> data = new ArrayList<>();
        String sql = "SELECT i.item_id,i.item_jenis,i.item_name,k.jumlah_item,k.harga_item_perjumlah FROM keranjang k JOIN item i on k.id_item = i.item_id AND k.order_id ="+id+";";
        db.executeQuery(sql);
        while(db.getRs().next()){
            int itemId = db.getRs().getInt("item_id");
            String itemJenis = db.getRs().getString("item_jenis");
            String namaItem = db.getRs().getString("item_name");
            int qty = db.getRs().getInt("jumlah_item");
            double priceItem = db.getRs().getDouble("harga_item_perjumlah");
            data.add(new OrderItem(itemId,itemJenis,namaItem,qty,priceItem));
        }   
        db.disconnectDB();
        return data;
    }
    public double getPrice(){
        return price_item;
    }
    public double setTotalPrice(){
        this.price_item = this.item.getPrice() * getQuantity();
        return this.price_item;
    }
    
}
