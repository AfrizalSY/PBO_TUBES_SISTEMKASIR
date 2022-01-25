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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author WIBU
 */
public class ItemController extends MouseAdapter implements ActionListener, BaseController {

    private Item_View view;
    private Cashier cashier = new Cashier();
    ArrayList<Item> item;
    public ItemController() {
        view = new Item_View();
        view.setTitle("Item Produk");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addActionListener(this);
        this.loadProduk();
        view.getTableProduk().setAutoCreateRowSorter(true);
        view.addMouseAdapter(this);
        view.getbtnUpdate().setEnabled(false);
        view.getbtnDelete().setEnabled(false);
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
            doDelete();            
        } else if (source.equals(view.getbtnReset())) {
            view.ResetForm();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source.equals(view.getTableProduk())&& me.getClickCount()==1){
            try{
                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow();
                String nama = (String) view.getTableProduk().getValueAt(row, 1);
                int index = -1;
                item = this.cashier.getAllItem();
                for(int i = 0; i < item.size() && index ==-1; i++){
                    Item currentData = item.get(i);
                    if(nama.equals(currentData.getName())){
                        index = i;
                    }
                }
                if(index != -1){
                    Item it = item.get(index);
                    if(it instanceof Food){
                        Food f = (Food) it;
                        if(f.getJenis().equals("Makanan")){
                            view.setBtnMakanan(true);
                        }else{
                            view.setBtnMinuman(true);
                        }
                        view.setTxtNama(f.getName());
                        view.getTxtNama().setEnabled(false);
                        view.getbtnUpdate().setEnabled(true);
                        view.getbtnDelete().setEnabled(true);
                        view.setTxtHarga(String.valueOf(f.getPrice()));
                        view.setTxtVoW(Integer.toString(f.getWeight()));
                    }else if(it instanceof Beverage){
                        Beverage Bf = (Beverage) it;
                        if(Bf.getJenis().equals("Makanan")){
                            view.setBtnMakanan(true);
                        }else{
                            view.setBtnMinuman(true);
                        }
                        view.setTxtNama(Bf.getName());
                        view.getTxtNama().setEnabled(false);
                        view.getbtnUpdate().setEnabled(true);
                        view.getbtnDelete().setEnabled(true);
                        view.setTxtHarga(String.valueOf(Bf.getPrice()));
                        view.setTxtVoW(Integer.toString(Bf.getVolume()));
                    }
                }
            }catch (SQLException ex) {
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        }
    }
    public void loadProduk() {
        try {
            DefaultTableModel tbl = (DefaultTableModel) view.getTableProduk().getModel();
            tbl.setRowCount(0);
            item = cashier.getAllItem();
//            if (!item.isEmpty()) {
                for (int i = 0; i < item.size(); i++) {
                    Item currentData = item.get(i);
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
//            }
        } catch (SQLException ex) {
            msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
        }
    }

    public void doTambahDanUpdate() {
        // if (view.getTxtNama().equals("") || view.getTxtHarga().equals("")||view.getTxtVoW().equals("")) {
        //     msg.showMessage("Data tidak boleh kosong", "Validasi error", 2);
        // }
        String jenis = "";
        if (view.getBtnMakanan().isSelected()) {
            jenis = "Makanan";
        } else if (view.getBtnMinuman().isSelected()) {
            jenis = "Minuman";
        }
        String name = view.getTxtNama().getText();
        String hargaTxt=view.getTxtHarga().getText();
        String VoWTxt= view.getTxtVoW().getText();
        if (jenis.isEmpty() || name.isEmpty() || hargaTxt.isEmpty() || VoWTxt.isEmpty()) {
            msg.showMessage("Data tidak boleh kosong", "Validasi error", 2);
        }else{
            double harga=0;
            int VoW=0;
            if(isNumber(hargaTxt)|| isNumber(VoWTxt)){
                harga = Double.parseDouble(hargaTxt);
                VoW = Integer.parseInt(VoWTxt);
                boolean found = false;
                try {
                    item = this.cashier.getAllItem();
                    // Food f = null;
                    // Beverage Bf = null;
                    for (int i = 0; i < item.size() && !found; i++) {
                        Item currentData = item.get(i);
                        if (currentData instanceof Food && name.equals(currentData.getName())) {
                            Food f = (Food) currentData;
                            f.setItem_Id(currentData.getItem_Id());
                            f.setName(name);
                            f.setPrice(harga);
                            f.setJenis(jenis);
                            f.setWeight(VoW);
                            found = true;
                            cashier.editItem(f);
                            msg.showMessage("Update data berhasil", "Success", 1);
                        } else if (currentData instanceof Beverage && name.equals(currentData.getName())) {
                            Beverage Bf = (Beverage) currentData;
                            Bf.setItem_Id(currentData.getItem_Id());
                            Bf.setName(name);
                            Bf.setPrice(harga);
                            Bf.setJenis(jenis);
                            Bf.setVolume(VoW);
                            found = true;
                            cashier.editItem(Bf);
                            msg.showMessage("Update data berhasil", "Success", 1);
                        }
                    }
                    if (found==false) {
                        if (jenis.equals("Makanan")) {
                            Food f = new Food(name, harga, jenis, VoW);
                            cashier.addItem(f);
                        } else if (jenis.equals("Minuman")) {
                            Beverage Bf = new Beverage(name, harga, jenis, VoW);
                            cashier.addItem(Bf);
                        }
                        msg.showMessage("Input data berhasil", "Success", 1);
                    }
                    loadProduk();
                    view.ResetForm();
                } catch (SQLException ex) {
                    msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
                }
            }else{
                msg.showMessage("Harga, Volume atau Berat Harus Dalam bentuk angka", "Validasi error", 2);
            }
        } 
    }
    public void doDelete(){
        try{
            // DefaultTableModel tbl = (DefaultTableModel) view.getTableProduk().getModel();
            item = cashier.getAllItem();
            int row = view.getTableProduk().getSelectedRow();
            int num_id = (int) view.getTableProduk().getValueAt(row,0);
            int idx = -1;
            for(int i =0; i<item.size() && idx == -1;i++){
                Item currentData = item.get(i);
                if(num_id==currentData.getItem_Id()){
                    String message = "Apakah Anda Yakin Menghapus Item Dengan Nama: "+currentData.getName();
                    Object[] options ={"Yes", "Cancel"};
                    int option = JOptionPane.showOptionDialog(null, message, "",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                    idx = i;
                    if(option == JOptionPane.OK_OPTION){  
                        cashier.deleteItem(num_id);
                        msg.showMessage("Data berhasil dihapus", "Success", 1);
                    }
                }
            }
            view.ResetForm();
            loadProduk();
        }catch (SQLException ex){
            msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
        }
    }

    public boolean isNumber(String s){
        for (int i = 0; i < s.length(); i++)
        if (Character.isDigit(s.charAt(i)) == false)
            return false;

        return true;
    }
}
