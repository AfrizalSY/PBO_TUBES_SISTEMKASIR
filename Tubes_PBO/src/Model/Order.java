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
public class Order {

    private int orderID;
    private int tableNumber;
    private String jenis_pembayaran;
    private String nama_bank;
    private double total_pembayaran;
    private double uang_yang_diberikan;
    private double uang_kembalian;
    private ArrayList<OrderItem> listItem;
    private Database db = new Database();

    public Order() {

    }

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.listItem = new ArrayList<>();

    }

    public Order(int orderID, int tableNumber, String jenis_pembayaran, String nama_bank, double total_pembayaran, double uang_yang_diberikan, double uang_kembalian) {
        this.orderID = orderID;
        this.tableNumber = tableNumber;
        this.jenis_pembayaran = jenis_pembayaran;
        this.nama_bank = nama_bank;
        this.total_pembayaran = total_pembayaran;
        this.uang_yang_diberikan = uang_yang_diberikan;
        this.uang_kembalian = uang_kembalian;
    }
    
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getJenis_pembayaran() {
        return jenis_pembayaran;
    }

    public void setJenis_pembayaran(String jenis_pembayaran) {
        this.jenis_pembayaran = jenis_pembayaran;
    }

    public String getNama_bank() {
        return nama_bank;
    }

    public void setNama_bank(String nama_bank) {
        this.nama_bank = nama_bank;
    }

    public double getTotal_pembayaran() {
        return total_pembayaran;
    }

    public void setTotal_pembayaran(double total_pembayaran) {
        this.total_pembayaran = total_pembayaran;
    }

    public double getUang_yang_diberikan() {
        return uang_yang_diberikan;
    }

    public void setUang_yang_diberikan(double uang_yang_diberikan) {
        this.uang_yang_diberikan = uang_yang_diberikan;
    }

    public double getUang_kembalian() {
        return uang_kembalian;
    }

    public void setUang_kembalian(double uang_kembalian) {
        this.uang_kembalian = uang_kembalian;
    }
    
    public ArrayList<OrderItem> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<OrderItem> listItem) {
        this.listItem = listItem;
    }

    public double getTotalPrice() {
        double total = 0f;
        for (OrderItem orderItem : listItem) {
            total += orderItem.setTotalPrice();
        }
        return total;
    }
    // Melakukann Update Total Price di OrderItem
    public void updateTotalPrice(int idOrder, double price) throws SQLException{
        db.connectDB();
        String sql = "UPDATE order_table SET total_pembayaran = '%s' WHERE id_order = %d";
        sql = String.format(sql, price, idOrder);
//        System.out.println(sql);
        db.executeUpdate(sql);
        db.disconnectDB();
    }

    public void getTotalItem() {
        int total = 0;
        for (OrderItem orderItem : listItem) {
            System.out.println("Item: " + orderItem.getItem().getName() + " \tQuantity:" + orderItem.getQuantity());
            total += orderItem.getQuantity();
        }
        System.out.println("Total Item: " + total);
    }
}
