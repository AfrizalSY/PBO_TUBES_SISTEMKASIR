/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cashier;
import Model.Order;
import View.ShowAllOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowAllOrderController extends MouseAdapter implements ActionListener, BaseController {

    private ShowAllOrder view;
    private int selectedItem = -1;
    private Cashier cashier = new Cashier();
    private ArrayList<Order> order;

    public ShowAllOrderController() {
        view = new ShowAllOrder();
        view.setTitle("Option");
        loadOrder();
        view.getBtnDetail().setEnabled(false);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addActionListener(this);
        view.addMouseAdapter(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnKembali())) {
            new OptionController();
            view.dispose();
        }else if(source.equals(view.getBtnDetail())){
            doDetail();
            view.dispose();
        }
    }
    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getTableAllOrder()) && me.getClickCount() == 1) {
            JTable target = (JTable) me.getSource();
            this.selectedItem = target.getSelectedRow();
            if (this.selectedItem != -1) {
//                System.out.println(this.selectedItem);                                                         
                view.getBtnDetail().setEnabled(true);
            }
        }
    }
    public void loadOrder(){
        try {
            DefaultTableModel tbl = (DefaultTableModel) view.getTableAllOrder().getModel();
            tbl.setRowCount(0);
            order = cashier.showAllOrder();
            if (!order.isEmpty()) {
                for (int i = 0; i < order.size(); i++) {
                    Order currentData = order.get(i);
                    tbl.insertRow(tbl.getRowCount(), new Object[]{
                        currentData.getOrderID(),
                        currentData.getTableNumber(),
                        currentData.getJenis_pembayaran(),
                        currentData.getNama_bank(),
                        currentData.getTotal_pembayaran(),
                        currentData.getUang_yang_diberikan(),
                        currentData.getUang_kembalian(),});
                }
            }
        } catch (SQLException ex) {
            msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
        }
    }
    public void doDetail(){
        Order ord = this.order.get(selectedItem);
        new Detail_Controller(ord);
    }
}
