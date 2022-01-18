/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Database.Database;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WIBU
 */
public class Cashier implements CashierInterface{
    private String name;
    private String username;
    private String password;
    private ArrayList<Order> listOrder;
    private ArrayList<Payment> listPayment;
    private Database db;
    
    public Cashier(String name, String user , String pass) {
        this.name = name;
        this.username = user;
        this.password = pass;
        this.listOrder = new ArrayList<>();
        this.listPayment = new ArrayList<>();        
    }
    public Cashier(String user, String pass){
        this.username = user;
        this.password = pass;
    }
    public Cashier(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ArrayList<Item> getAllItem() throws SQLException{
        db.connectDB();
        ArrayList<Item> data = new ArrayList<>();
        String sql = "SELECT * FROM Item";
        db.executeQuery(sql);
        while(db.getRs().next()){
            String name = db.getRs().getString("item_name");
            double price = db.getRs().getDouble("item_price");
            String jenis = db.getRs().getString("item_jenis");
            int VoW = db.getRs().getInt("volume_or_weight");
            if(jenis.equals("Makanan")){
                data.add(new Food(name,price,jenis,VoW));
            }else if (jenis.equals("Minuman")){
                data.add(new Beverage(name,price,jenis,VoW));
            }
        }
        return data;
    }
    public Order createOrder(int tableNo){
        return new Order(tableNo);        
    }
    @Override
    public void addItem(Item n, int VoW) throws SQLException{
        db.connectDB();
        String sql = "INSERT INTO 'Item'"
                + "('item_id','item_name','item_price','item_jenis','volume_or_weight')" 
                + "VALUES(NULL,'%s', '%s', '%s', '%s');";
        if(n instanceof Food){
            Food f = (Food) n;
            VoW = f.getWeight();
            sql = String.format(sql,f.getName(),f.getPrice(),f.getJenis(),VoW);
        }else if(n instanceof Beverage){
            Beverage Bf = (Beverage) n;
            VoW = Bf.getVolume();
            sql = String.format(sql,Bf.getName(),Bf.getPrice(),Bf.getJenis(),VoW);
        }
        db.execute(sql);
    }

    @Override
    public void addItem(Item n,int qty, Order o) {
        o.addOrderItem(n, qty);
    }

    @Override
    public void editItem(Item n, int VoW) throws SQLException {
        db.connectDB();
        String sql = "UPDATE 'Item' SET 'Item_price' = '%f', 'item_jenis' = '%s','volume_or_weight' = '%d' WHERE 'Item'.'item_name' = '%s'";
        if(n instanceof Food){
            Food f = (Food) n;
            VoW = f.getWeight();
            sql = String.format(sql,f.getName(),f.getPrice(),f.getJenis(),VoW);
        }else if(n instanceof Beverage){
            Beverage Bf = (Beverage) n;
            VoW = Bf.getVolume();
            sql = String.format(sql,Bf.getName(),Bf.getPrice(),Bf.getJenis(),VoW);
        }
        db.execute(sql);
    }

    // @Override
    // public void editItem(Item n, Order o) {
        
    // }

    @Override
    public void deleteItem(Item n) {
        
    }

    @Override
    public void deleteItem(Item n, Order o) {
        
    }
    
    public void showAllPayment(Payment p){
        
    }
    
    public void showAllOrder(){
        
    }
}
