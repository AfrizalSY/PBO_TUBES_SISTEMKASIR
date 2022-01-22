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
    private Database db = new Database();
    
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
        String sql = "SELECT * FROM item";
        db.executeQuery(sql);
        while(db.getRs().next()){
            int id = db.getRs().getInt("item_id");
            String name = db.getRs().getString("item_name");
            double price = db.getRs().getDouble("item_price");
            String jenis = db.getRs().getString("item_jenis");
            int VoW = db.getRs().getInt("volume_or_weight");
            if(jenis.equals("Makanan")){
                data.add(new Food(id,name,price,jenis,VoW));
            }else if (jenis.equals("Minuman")){
                data.add(new Beverage(id,name,price,jenis,VoW));
            }
        }
        db.disconnectDB();
        return data;
    }
    public Order createOrder(int tableNo){
        return new Order(tableNo);        
    }
    @Override
    public void addItem(Food f) throws SQLException{
        db.connectDB();
        String sql = "INSERT INTO item (item_id,item_name,item_price,item_jenis,volume_or_weight) "
                + "VALUES(NULL,'"+f.getName()+"', "+f.getPrice()+", '"+f.getJenis()+"', "+f.getWeight()+");";
        db.execute(sql);
        db.disconnectDB();
    }

    @Override
    public void addItem(Beverage Bf) throws SQLException{
        db.connectDB();
        String sql = "INSERT INTO item (item_id,item_name,item_price,item_jenis,volume_or_weight) "
                + "VALUES(NULL,'"+Bf.getName()+"', "+Bf.getPrice()+", '"+Bf.getJenis()+"', "+Bf.getVolume()+");";
        // sql = String.format(sql,Bf.getName(),Bf.getPrice(),Bf.getJenis(),Bf.getVolume());
        db.execute(sql);
        db.disconnectDB();
    }

    @Override
    public void addItem(Item n,int qty, Order o) {
        o.addOrderItem(n, qty);
    }

    @Override
    public void editItem(Food f) throws SQLException {
        db.connectDB();
        String sql = "UPDATE item SET Item_price = "+f.getPrice()+", item_jenis = '"+f.getJenis()+
                    "',volume_or_weight = "+f.getWeight()+" WHERE item_name = '"+f.getName()+"'";
        db.executeUpdate(sql);
        db.disconnectDB();
    }
    @Override
    public void editItem(Beverage Bf) throws SQLException {
        db.connectDB();
        String sql = "UPDATE item SET Item_price = "+Bf.getPrice()+", item_jenis = '"+Bf.getJenis()+
                    "',volume_or_weight = "+Bf.getVolume()+" WHERE item_name = '"+Bf.getName()+"'";
        db.executeUpdate(sql);
        db.disconnectDB();
    }
    // @Override
    // public void editItem(Item n, Order o) {
        
    // }

    @Override
    public void deleteItem(int idx) throws SQLException {
        db.connectDB();
        String sql = "DELETE FROM item WHERE item.item_id="+ idx;
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    @Override
    public void deleteItem(Item n, Order o) {
        
    }
    
    public void showAllPayment(Payment p){
        
    }
    
    public void showAllOrder(){
        
    }
}
