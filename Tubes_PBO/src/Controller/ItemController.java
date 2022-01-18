/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Item_View;
import Model.Item;
import Model.Food;
import Model.Beverage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import Model.Cashier;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author WIBU
 */
public class ItemController extends MouseAdapter implements ActionListener, BaseController{
    private Item_View frame;
    private Cashier cashier;
    public ItemController(){
        frame = new Item_View();
        this.loadProduk();
        frame.setTitle("Item Produk");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getTableProduk().setAutoCreateRowSorter(true);
        frame.addActionListener(this);
        frame.addMouseAdapter(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(frame.getbtnKembali())) {
            new OptionController();
            frame.dispose();
        } else if (source.equals(frame.getbtnUpdate())) {

        } else if(source.equals(frame.getbtnTambah())){

        } else if(source.equals(frame.getbtnDelete())){

        }
    }
    public void mousePressed(MouseEvent me) {
        // Object source = me.getSource();
        // if(source.equals(frame.getTableProduk())&& me.getClickCount()==2){
        //     JTable target = (JTable) me.getTarget();
        // }
    }
    public void loadProduk(){
        try{
            DefaultTableModel tbl = (DefaultTableModel) frame.getTableProduk().getModel();
            tbl.setRowCount(0);
            ArrayList<Item> data = this.cashier.getAllItem();
            for(int i =0; i<data.size();i++){
                Item currentData = data.get(i);
                if (currentData instanceof Food){
                    Food f = (Food) currentData;
                    tbl.insertRow(tbl.getRowCount(),new Object[]{
                        f.getItem_Id(),
                        f.getName(),
                        f.getPrice(),
                        f.getJenis(),
                        f.getWeight(),
                    });
                }else if (currentData instanceof Beverage){
                    Beverage Bf = (Beverage) currentData;
                    tbl.insertRow(tbl.getRowCount(),new Object[]{
                        Bf.getItem_Id(),
                        Bf.getName(),
                        Bf.getPrice(),
                        Bf.getJenis(),
                        Bf.getVolume(),
                    });
                }
            }
        }catch(SQLException ex){
            msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
        }
    }
    public void doTambahDanUpdate(){
        String jenis = "";
        if(frame.getBtnMakanan().isSelected()){
            jenis = "Makanan";
        }else if(frame.getBtnMinuman().isSelected()){
            jenis = "Minuman";
        }
        String name = frame.getTxtNama().getText();
        String hargaTxt = frame.getTxtHarga().getText();
        double harga = Double.parseDouble(hargaTxt );
        String VoWTxt=frame.getTxtVoW().getText();
        int VoW = Integer.parseInt(VoWTxt);
        if(jenis.isEmpty() || name.isEmpty() || hargaTxt.isEmpty()|| VoWTxt.isEmpty()) {
            msg.showMessage("Data tidak boleh negatif", "Validasi error", 2);
        }else{
            boolean found = false;
            try{
                ArrayList<Item> data = this.cashier.getAllItem();
                for(int i =0; i<data.size()&&!found;i++){
                    Item currentData = data.get(i);
                    if (currentData instanceof Food && name.equals(currentData.getName())){
                        Food f = (Food) currentData;
                        // f.setItem_Id(currentData.getItem_Id());
                        f.setName(name);
                        f.setPrice(harga);
                        f.setJenis(jenis);
                        f.setWeight(VoW);
                        found = true;
                    }else if (currentData instanceof Beverage&&name.equals(currentData.getName())){
                        Beverage Bf = (Beverage) currentData;
                        // Bf.getItem_Id():
                        Bf.setName(name);
                        Bf.setPrice(harga);
                        Bf.setJenis(jenis);
                        Bf.setVolume(VoW);
                        found = true;
                    }
                }
                if(!found){
                    this.cashier.addItem(new Item(name, harga,jenis),VoW);
                    msg.showMessage("Input data berhasil", "Success", 1);
                    this.loadProduk();
                }else{
                    this.cashier.editItem(new Item(name, harga,jenis), VoW);
                    msg.showMessage("Data berhasil diupdate", "Success", 1);
                    this.loadProduk();
                }
            }catch(SQLException ex){
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        }
    }
}