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
public class Cashier implements CashierInterface {

    private String name;
    private String username;
    private String password;
    private ArrayList<Order> listOrder;
    private Database db = new Database();

    public Cashier(String name, String user, String pass) {
        this.name = name;
        this.username = user;
        this.password = pass;
        this.listOrder = new ArrayList<>();
    }

    public Cashier(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public Cashier() {

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

    public ArrayList<Item> getAllItem() throws SQLException {
        db.connectDB();
        ArrayList<Item> data = new ArrayList<>();
        String sql = "SELECT * FROM item";
        db.executeQuery(sql);
        while (db.getRs().next()) {
            int id = db.getRs().getInt("item_id");
            String name = db.getRs().getString("item_name");
            double price = db.getRs().getDouble("item_price");
            String jenis = db.getRs().getString("item_jenis");
            int VoW = db.getRs().getInt("volume_or_weight");
            if (jenis.equals("Makanan")) {
                data.add(new Food(id, name, price, jenis, VoW));
            } else if (jenis.equals("Minuman")) {
                data.add(new Beverage(id, name, price, jenis, VoW));
            }
        }
        db.disconnectDB();
        return data;
    }

    public int createOrder(int tableNo) throws SQLException {
        db.connectDB();
        String sql = "INSERT INTO `order_table` (`no_table`) VALUES ('%s');";
        sql = String.format(sql, tableNo);
        int newOrderId = db.executeInsert(sql);
        db.disconnectDB();
        return newOrderId;
    }

    @Override
    public void addItem(Food f) throws SQLException {
        db.connectDB();
        String sql = "INSERT INTO item (item_id,item_name,item_price,item_jenis,volume_or_weight) "
                + "VALUES(NULL,'" + f.getName() + "', " + f.getPrice() + ", '" + f.getJenis() + "', " + f.getWeight() + ");";
        db.execute(sql);
        db.disconnectDB();
    }

    @Override
    public void addItem(Beverage Bf) throws SQLException {
        db.connectDB();
        String sql = "INSERT INTO item (item_id,item_name,item_price,item_jenis,volume_or_weight) "
                + "VALUES(NULL,'" + Bf.getName() + "', " + Bf.getPrice() + ", '" + Bf.getJenis() + "', " + Bf.getVolume() + ");";
        // sql = String.format(sql,Bf.getName(),Bf.getPrice(),Bf.getJenis(),Bf.getVolume());
        db.execute(sql);
        db.disconnectDB();
    }



    @Override
    public void editItem(Food f) throws SQLException {
        db.connectDB();
        String sql = "UPDATE item SET Item_price = " + f.getPrice() + ", item_jenis = '" + f.getJenis()
                + "',volume_or_weight = " + f.getWeight() + " WHERE item_name = '" + f.getName() + "'";
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    @Override
    public void editItem(Beverage Bf) throws SQLException {
        db.connectDB();
        String sql = "UPDATE item SET Item_price = " + Bf.getPrice() + ", item_jenis = '" + Bf.getJenis()
                + "',volume_or_weight = " + Bf.getVolume() + " WHERE item_name = '" + Bf.getName() + "'";
        db.executeUpdate(sql);
        db.disconnectDB();
    }
    // @Override
    // public void editItem(Item n, Order o) {

    // }
    @Override
    public void deleteItem(int idx) throws SQLException {
        db.connectDB();
        String sql = "DELETE FROM item WHERE item.item_id=" + idx;
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    @Override
    public void deleteItem(int itemId, int orderId) throws SQLException {
        db.connectDB();
        String sql = "DELETE FROM keranjang WHERE id_item = %d AND order_id = %d";
        sql = String.format(sql, itemId, orderId);
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    public ArrayList<Order> showAllOrder() throws SQLException {
        db.connectDB();
        listOrder = new ArrayList<>();
        String sql = "SELECT * FROM order_table";
        db.executeQuery(sql);
        while (db.getRs().next()) {
            int id = db.getRs().getInt("id_order");
            int noTable = db.getRs().getInt("no_table");
            String jenis_pembayaran = db.getRs().getString("jenis_pembayaran");
            String nama_bank = db.getRs().getString("nama_bank");
            double total_pembayaran = db.getRs().getDouble("total_pembayaran");
            double uang_yang_diberikan = db.getRs().getDouble("uang_yang_diberikan");
            double uang_kembalian = db.getRs().getDouble("uang_kembalian");
            listOrder.add(new Order(id,noTable,jenis_pembayaran,nama_bank,total_pembayaran,uang_yang_diberikan,uang_kembalian));
        }
        db.disconnectDB();
        return listOrder;
    }

    public void payWithCash(double totalPrice, double cash, double kembalian, int orderId) throws SQLException {
        db.connectDB();
        String sql = "UPDATE order_table SET  jenis_pembayaran='cash', total_pembayaran='%s', nama_bank = '-',uang_yang_diberikan = '%s', uang_kembalian = '%s' WHERE id_order = %d";
        sql = String.format(sql, totalPrice, cash, kembalian, orderId);
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    public void payWithBank(double totalPrice, String bankName, double cash, double kembalian, int orderId) throws SQLException {
        db.connectDB();
        String sql = "UPDATE order_table SET jenis_pembayaran='bank', total_pembayaran='%s', nama_bank = '%s', uang_yang_diberikan = '%s', uang_kembalian = '%s' WHERE id_order = %d";
        sql = String.format(sql, totalPrice, bankName, cash, kembalian, orderId);
        db.executeUpdate(sql);
        db.disconnectDB();
    }
}
