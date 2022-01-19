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
import Model.Cashier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author WIBU
 */
public class ItemController extends MouseAdapter implements ActionListener, BaseController {

    private Item_View view;
    private Cashier cashier = new Cashier();

    public ItemController() {
        view = new Item_View();
        view.setTitle("Item Produk");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addActionListener(this);
        System.out.println("PUNTEN");
        this.loadProduk();
        view.getTableProduk().setAutoCreateRowSorter(true);
        view.addMouseAdapter(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getbtnKembali())) {
            new OptionController();
            view.dispose();
        } else if (source.equals(view.getbtnUpdate())) {
            doTambahDanUpdate();
        } else if (source.equals(view.getbtnTambah())) {
            doTambahDanUpdate();
        } else if (source.equals(view.getbtnDelete())) {

        } else if (source.equals(view.getbtnReset())) {
            view.ResetForm();
        }
    }

    // @Override
    // public void mousePressed(MouseEvent me) {
    //     Object source = me.getSource();
    //     if(source.equals(view.getTableProduk())&& me.getClickCount()==2){
    //         try{
    //             JTable target = (JTable) me.getSource();
    //             int row = target.getSelectedRow();
    //             String nama = (String) view.getTableProduk().getValueAt(row, 1);
    //             int index = -1;
    //             ArrayList<Item> data = this.cashier.getAllItem();
    //             for(int i = 0; i < data.size() && index ==-1; i++){
    //                 Item currentData = data.get(i);
    //                 if(nama.equals(currentData.getName())){
    //                     index = i;
    //                 }
    //             }
    //             if(index == -1){
    //                 Item it = data.get(index);
    //                 if(it instanceof Food){
    //                     Food f = (Food) it;
    //                     if(f.getJenis().equals("Makanan")){
    //                         view.setBtnMakanan();
    //                     }else{
    //                         view.setBtnMinuman();
    //                     }
    //                     view.setTxtNama(f.getName());
    //                     view.setTxtHarga(String.valueOf(f.getPrice()));
    //                     view.setTxtVoW(Integer.toString(f.getWeight()));
    //                 }else if(it instanceof Beverage){
    //                     Beverage Bf = (Beverage) it;
    //                     if(Bf.getJenis().equals("Makanan")){
    //                         view.setBtnMakanan();
    //                     }else{
    //                         view.setBtnMinuman();
    //                     }
    //                     view.setTxtNama(Bf.getName());
    //                     view.setTxtHarga(String.valueOf(Bf.getPrice()));
    //                     view.setTxtVoW(Integer.toString(Bf.getVolume()));
    //                 }
    //             }
    //         }catch (SQLException ex) {
    //             msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
    //         }
    //     }
    // }
    public void loadProduk() {
        try {
            DefaultTableModel tbl = (DefaultTableModel) view.getTableProduk().getModel();
            tbl.setRowCount(0);
            System.out.println("PUNTEN222");
            ArrayList<Item> data = cashier.getAllItem();
            System.out.println(data);
            if (!data.isEmpty()) {
                for (int i = 0; i < data.size(); i++) {
                    Item currentData = data.get(i);
                    // tbl.insertRow(tbl.getRowCount(),new Object[]{
                    //     f.getItem_Id(),
                    //     f.getName(),
                    //     f.getPrice(),
                    //     f.getJenis(),
                    //     f.getWeight(),});
                    if (currentData instanceof Food) {
                        Food f = (Food) currentData;
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            f.getItem_Id(),
                            f.getName(),
                            f.getPrice(),
                            f.getJenis(),
                            f.getWeight(),});
                    } else if (currentData instanceof Beverage) {
                        Beverage Bf = (Beverage) currentData;
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            Bf.getItem_Id(),
                            Bf.getName(),
                            Bf.getPrice(),
                            Bf.getJenis(),
                            Bf.getVolume(),});
                    }
                }
            }
        } catch (SQLException ex) {
            msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
        }
    }

    public void doTambahDanUpdate() {
        String jenis = "";
        if (view.getBtnMakanan().isSelected()) {
            jenis = "Makanan";
        } else if (view.getBtnMinuman().isSelected()) {
            jenis = "Minuman";
        }
        String name = view.getTxtNama().getText();
        String hargaTxt = view.getTxtHarga().getText();
        double harga = Double.parseDouble(hargaTxt);
        String VoWTxt = view.getTxtVoW().getText();
        int VoW = Integer.parseInt(VoWTxt);
        if (jenis.isEmpty() || name.isEmpty() || hargaTxt.isEmpty() || VoWTxt.isEmpty()) {
            msg.showMessage("Data tidak boleh kosong", "Validasi error", 2);
        } else {
            boolean found = false;
            try {
                ArrayList<Item> data = this.cashier.getAllItem();
                // Food f = null;
                // Beverage Bf = null;
                for (int i = 0; i < data.size() && !found; i++) {
                    Item currentData = data.get(i);
                    if (currentData instanceof Food && name.equals(currentData.getName())) {
                        Food f = (Food) currentData;
                        f.setItem_Id(currentData.getItem_Id());
                        f.setName(name);
                        f.setPrice(harga);
                        f.setJenis(jenis);
                        f.setWeight(VoW);
                        found = true;
                    } else if (currentData instanceof Beverage && name.equals(currentData.getName())) {
                        Beverage Bf = (Beverage) currentData;
                        Bf.setItem_Id(currentData.getItem_Id());
                        Bf.setName(name);
                        Bf.setPrice(harga);
                        Bf.setJenis(jenis);
                        Bf.setVolume(VoW);
                        found = true;
                    }
                }
                if (!found) {
                    if (jenis.equals("Makanan")) {
                        Food f = new Food(name, harga, jenis, VoW);
                        this.cashier.addItem(f);
                    } else if (jenis.equals("Minuman")) {
                        Beverage Bf = new Beverage(name, harga, jenis, VoW);
                        this.cashier.addItem(Bf);
                    }
                    msg.showMessage("Input data berhasil", "Success", 1);
                    this.loadProduk();
                }
                //      if(f.getJenis().equals("Makanan")){
                //         Item fi = (Food) f;
                //         this.cashier.editItem(fi);
                //      }else if(Bf.getJenis().equals("Makanan")){
                //         Item fi = (Beverage) 
                //         this.cashier.editItem(new Item(name, harga,jenis), VoW);
                //      }

                //      msg.showMessage("Data berhasil diupdate", "Success", 1);
                //      this.loadProduk();
                //  }
            } catch (SQLException ex) {
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        }
    }
    public void doDelete(){
        
    }
}
