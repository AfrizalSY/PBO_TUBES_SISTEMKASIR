/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Item;
import Model.Food;
import Model.Beverage;
import Model.Cashier;
import View.Order_View;
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
public class OrderController extends MouseAdapter implements ActionListener, BaseController{
    private Order_View view;
    private Cashier cashier = new Cashier();
    public OrderController(){
        view = new Order_View();
        view.setTitle("Option");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loadItem();
        view.getBtnBayar().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnTambahOrder().setEnabled(false);
        view.getTableItem().setAutoCreateRowSorter(true);
        view.getTableKeranjang().setAutoCreateRowSorter(true);
        view.addActionListener(this);
        view.addMouseAdapter(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnTambahOrder())) {
            doTambahKeKeranjang();
        } else if (source.equals(view.getBtnBayar())) {
            new BayarController();
            // view.dispose();
        } else if(source.equals(view.getBtnHapus())){
        } else if(source.equals(view.getBtnKembali())){
            new OptionController();
            view.dispose();
        }
    }
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source.equals(view.getTableItem())&& me.getClickCount()==2){
            try{
                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow();
                int item_id= (int) view.getTableItem().getValueAt(row, 0);
                int index = -1;
                ArrayList<Item> data = this.cashier.getAllItem();
                for(int i = 0; i < data.size() && index ==-1; i++){
                    Item currentData = data.get(i);
                    if(item_id==currentData.getItem_Id()){
                        System.out.println("Test");
                        index = i;
                    }
                }
                if(index != -1){
                    // view.setItemId(view.getTableItem().getValueAt(row, 0).toString());
                    // view.setLblJenis(view.getTableItem().getValueAt(row, 1).toString());
                    // view.setLblItem(view.getTableItem().getValueAt(row, 2).toString());
                    // view.setLblHarga(view.getTableItem().getValueAt(row, 3).toString());
                    view.getBtnTambahOrder().setEnabled(true);
                    Item it = data.get(index);
                    if(it instanceof Food){
                        Food f = (Food) it;
                        view.setItemId(Integer.toString(f.getItem_Id()));
                        view.setLblItem(f.getName());
                        view.setLblJenis(f.getJenis());
                        view.setLblHarga(String.valueOf(f.getPrice()));
                    }else if(it instanceof Beverage){
                        Beverage Bf = (Beverage) it;
                        view.setItemId(Integer.toString(Bf.getItem_Id()));
                        view.setLblItem(Bf.getName());
                        view.setLblJenis(Bf.getJenis());
                        view.setLblHarga(String.valueOf(Bf.getPrice()));
                    }
                }
            }catch (SQLException ex) {
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        }
    }
    public void loadItem() {
        try {
            DefaultTableModel tbl = (DefaultTableModel) view.getTableItem().getModel();
            tbl.setRowCount(0);
            ArrayList<Item> data = cashier.getAllItem();
            if (!data.isEmpty()) {
                for (int i = 0; i < data.size(); i++) {
                    Item currentData = data.get(i);
                    if (currentData instanceof Food) {
                        Food f = (Food) currentData;
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            f.getItem_Id(),
                            f.getJenis(),
                            f.getName(),
                            f.getPrice(),});
                    } else if (currentData instanceof Beverage) {
                        Beverage Bf = (Beverage) currentData;
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            Bf.getItem_Id(),
                            Bf.getJenis(),
                            Bf.getName(),
                            Bf.getPrice(),});
                    }
                }
            }
        } catch (SQLException ex) {
            msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
        }
    }
    public void doTambahKeKeranjang(){
        String txtNoMeja = view.getTxtFieldNoMeja().getText();
        String txtKuantitas = view.getTxtFieldKuantitas().getText();
        if(txtNoMeja.isEmpty()|| txtKuantitas.isEmpty()) {
            msg.showMessage("No Meja dan Jumlah Kuantitas Tidak Boleh Kosong", "Validasi error", 2);
        }else{
            int noMeja = Integer.parseInt(txtNoMeja);
            int kuantitas = Integer.parseInt(txtKuantitas);
            
        }
    }
}