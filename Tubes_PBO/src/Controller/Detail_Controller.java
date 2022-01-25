/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import static Controller.BaseController.msg;
import View.DetailOrderView;
import Model.Order;
import Model.OrderItem;
import Model.Cashier;
import javax.swing.JFrame;
import Model.OrderItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author WIBU
 */
public class Detail_Controller implements ActionListener, BaseController{
    private DetailOrderView view;
    private ArrayList<OrderItem> orderitem;
    private OrderItem oi = new OrderItem();
    public Detail_Controller(){
        view = new DetailOrderView();
        view.setTitle("Detail Pemesanan");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addActionListener(this);
    }
    public Detail_Controller(Order o) {
        view = new DetailOrderView();
        view.setTitle("Detail Pemesanan");
        view.setVisible(true);
        view.addActionListener(this);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setLblOrderId(Integer.toString(o.getOrderID()));
        view.setLblNoMeja(Integer.toString(o.getTableNumber()));
        view.setLblJenisPembayaran(o.getJenis_pembayaran());
        view.setLblNamaBank(o.getNama_bank());
        view.setLblTotalPembayaran(String.valueOf(o.getTotal_pembayaran()));
        view.setLblUangYgDiberikan(String.valueOf(o.getUang_yang_diberikan()));
        view.setLblKembalian(String.valueOf(o.getUang_kembalian()));
        loadKeranjang(o.getOrderID());
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnKembali())) {
            new ShowAllOrderController();
            view.dispose();
        }
    }
    public void loadKeranjang(int id) {
        if (id != 0) {
            try {
                DefaultTableModel tbl = (DefaultTableModel) view.getTblKeranjang().getModel();
                tbl.setRowCount(0);
                orderitem = oi.getAllOrderItem(id);
                if (! orderitem.isEmpty()) {
                    for (int i = 0; i <  orderitem.size(); i++) {
                        OrderItem currentData =  orderitem.get(i);
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            currentData.getItemId(),
                            currentData.getItem_jenis(),
                            currentData.getNamaItem(),
                            currentData.getQuantity(),
                            currentData.getPrice(),});
                    }
                } 
            } catch (SQLException ex) {
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        }
    }
}
