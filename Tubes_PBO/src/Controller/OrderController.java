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
import View.Order;
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
public class OrderController implements ActionListener, BaseController{
    private Order view;
    private Cashier cashier = new Cashier();
    public OrderController(){
        view = new Order();
        view.setTitle("Option");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loadItem();
        view.getTableItem().setAutoCreateRowSorter(true);
        view.getTableKeranjang().setAutoCreateRowSorter(true);
        view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnTambahOrder())) {
        } else if (source.equals(view.getBtnBayar())) {
            new BayarController();
            // view.dispose();
        } else if(source.equals(view.getBtnHapus())){
        } else if(source.equals(view.getBtnKembali())){
            new OptionController();
            view.dispose();
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
                    // tbl.insertRow(tbl.getRowCount(),new Object[]{
                    //     f.getItem_Id(),
                    //     f.getName(),
                    //     f.getPrice(),
                    //     f.getJenis(),
                    //     f.getWeight(),});
                    if (currentData instanceof Food) {
                        Food f = (Food) currentData;
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            f.getJenis(),
                            f.getName(),
                            f.getPrice(),});
                    } else if (currentData instanceof Beverage) {
                        Beverage Bf = (Beverage) currentData;
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
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
}