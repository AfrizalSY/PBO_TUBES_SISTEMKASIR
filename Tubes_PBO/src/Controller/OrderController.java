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
import Model.OrderItem;
import Model.Order;
import View.Order_View;
import Database.Database;
import Model.Bank;
import Model.Cash;
import Model.Payment;
import View.Bayar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author WIBU
 */
public class OrderController extends MouseAdapter implements ActionListener, BaseController {

    private Order_View view;
    private Cashier cashier = new Cashier();
    private Bayar bayarView;
    private OrderItem oi = new OrderItem();
    private Order order = new Order();
    private Database db = new Database();
    private int idOrder = 0;
    private int selectedItem = -1;
    private ArrayList<OrderItem> items;
    private double total = 0;
    private Payment payment;

    public OrderController() {
        view = new Order_View();
        bayarView = new Bayar();
        view.setTitle("Option");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bayarView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loadItem();
        view.getBtnBayar().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnTambahOrder().setEnabled(false);
        view.getTableItem().setAutoCreateRowSorter(true);
        view.getTableKeranjang().setAutoCreateRowSorter(true);

        view.addActionListener(this);
        view.addMouseAdapter(this);
        bayarView.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnTambahOrder())) {
            doTambahKeKeranjang();
            loadKeranjang();
        } else if (source.equals(view.getBtnBayar())) {
            showBayar();
            //new BayarController();
            // view.dispose();
        } else if (source.equals(view.getBtnHapus())) {
            doDelete();
            loadKeranjang();
        } else if (source.equals(view.getBtnKembali())) {
            new OptionController();
            view.dispose();
        } else if (source.equals(bayarView.getBtnBayar())) {
            doBayar();
        } else if (source.equals(bayarView.getBtnKembali())) {
            bayarView.setVisible(false);
        } else if (source.equals(bayarView.getMthdBank())) {
            doBank();
        } else if (source.equals(bayarView.getMthdCash())) {
            doCash();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getTableItem()) && me.getClickCount() == 1) {
            try {
                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow();
                int item_id = (int) view.getTableItem().getValueAt(row, 0);
                int index = -1;
                ArrayList<Item> data = this.cashier.getAllItem();
                for (int i = 0; i < data.size() && index == -1; i++) {
                    Item currentData = data.get(i);
                    if (item_id == currentData.getItem_Id()) {
                        index = i;
                    }
                }
                if (index != -1) {
                    view.getBtnTambahOrder().setEnabled(true);
                    Item it = data.get(index);
                    if (it instanceof Food) {
                        Food f = (Food) it;
                        view.setItemId(Integer.toString(f.getItem_Id()));
                        view.setLblItem(f.getName());
                        view.setLblJenis(f.getJenis());
                        view.setLblHarga(String.valueOf(f.getPrice()));
                    } else if (it instanceof Beverage) {
                        Beverage Bf = (Beverage) it;
                        view.setItemId(Integer.toString(Bf.getItem_Id()));
                        view.setLblItem(Bf.getName());
                        view.setLblJenis(Bf.getJenis());
                        view.setLblHarga(String.valueOf(Bf.getPrice()));
                    }
                }
            } catch (SQLException ex) {
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        } else if (source.equals(view.getTableKeranjang()) && me.getClickCount() == 1) {
            JTable target = (JTable) me.getSource();
            this.selectedItem = target.getSelectedRow();
            if (this.selectedItem != -1) {
                view.getBtnHapus().setEnabled(true);
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

    public void loadKeranjang() {
        if (this.idOrder != 0) {
            try {
                DefaultTableModel tbl = (DefaultTableModel) view.getTableKeranjang().getModel();
                tbl.setRowCount(0);
                this.items = oi.getAllOrderItem(this.idOrder);
                if (!items.isEmpty()) {
                    this.total = 0;
                    for (int i = 0; i < items.size(); i++) {
                        OrderItem currentData = items.get(i);
                        this.total += currentData.getPrice();
                        tbl.insertRow(tbl.getRowCount(), new Object[]{
                            currentData.getItemId(),
                            currentData.getItem_jenis(),
                            currentData.getNamaItem(),
                            currentData.getQuantity(),
                            currentData.getPrice(),});
                    }
                    this.order.updateTotalPrice(this.idOrder, total);
                    this.view.getLblTotal().setText(String.valueOf(total));
                } else {
                    this.total = 0;
                    this.order.updateTotalPrice(this.idOrder, 0);
                    this.view.getLblTotal().setText(String.valueOf(0));
                }
            } catch (SQLException ex) {
                msg.showMessage("Gagal mendapatkan data: " + ex.getMessage(), "Database error", 2);
            }
        }
    }

    public void doTambahKeKeranjang() {
        try {
            String txtNoMeja = view.getTxtFieldNoMeja().getText();
            String txtKuantitas = view.getTxtFieldKuantitas().getText();
            if (txtNoMeja.isEmpty() || txtKuantitas.isEmpty()) {
                msg.showMessage("No Meja dan Jumlah Kuantitas Tidak Boleh Kosong", "Validasi error", 2);
            } else {
                if (isNumber(txtNoMeja) || isNumber(txtKuantitas)) {
                    int itemId = Integer.parseInt(view.getItemId().getText());
                    int noMeja = Integer.parseInt(txtNoMeja);
                    int kuantitas = Integer.parseInt(txtKuantitas);
                    double harga_per_item = Double.parseDouble(view.getLblHarga().getText());
                    harga_per_item *= kuantitas;
                    DefaultTableModel tbl = (DefaultTableModel) view.getTableKeranjang().getModel();
                    if (tbl.getRowCount() == 0) {
                        this.idOrder = this.cashier.createOrder(noMeja);
                    }
                    // insert to keranjang dengan id order IdOrder & item id = selected item
                    db.connectDB();
                    String sql = "Insert INTO keranjang (keranjang_id, order_id,id_item,jumlah_item,harga_item_perjumlah)"
                            + " VALUES(NULL," + this.idOrder + "," + itemId + "," + kuantitas + "," + harga_per_item + ");";
                    //                System.out.println(sql);
                    db.executeUpdate(sql);
                    view.getBtnBayar().setEnabled(true);
                    view.ResetLabel();
                    view.getBtnTambahOrder().setEnabled(false);
                    view.getTxtFieldNoMeja().setEnabled(false);
                } else {
                    msg.showMessage("No Meja atau Kuantitas Harus Angka", "Validasi error", 2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doDelete() {
        try {
            DefaultTableModel tbl = (DefaultTableModel) view.getTableKeranjang().getModel();
            if (tbl.getRowCount() <= 1) {
                msg.showMessage("Pesanan tidak boleh kosong", "Validasi error", 2);
            } else {
                int idItem = this.items.get(selectedItem).getItemId();
                this.cashier.deleteItem(idItem, this.idOrder);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showBayar() {
        bayarView.getTotal().setText(String.valueOf(this.total));
        bayarView.getCmbNamaBank().setEnabled(false);
        bayarView.setVisible(true);
    }

    public void doBank() {
        Bank bank = new Bank(this.total);
        bayarView.getLblTotalWTax().setText(String.valueOf(bank.includeTax()));
        bayarView.getCmbNamaBank().setEnabled(true);
    }

    public void doCash() {
        Cash cash = new Cash(this.total);
        bayarView.getLblTotalWTax().setText(String.valueOf(cash.includeTax()));
        bayarView.getCmbNamaBank().setEnabled(false);
    }

    public void doBayar() {
        try {
            if (bayarView.getTxtFieldBayar().getText().isEmpty()) {
                msg.showMessage("Pembayaraan Harus Diisi", "Validasi error", 2);
            } else {
                if (isNumber(bayarView.getTxtFieldBayar().getText())) {
                    Double totalPrice = Double.parseDouble(this.bayarView.getLblTotalWTax().getText());

                    Double cash = Double.parseDouble(this.bayarView.getTxtFieldBayar().getText());
                    Double kembalian = Double.parseDouble(this.bayarView.getKembalian().getText());
                    if (kembalian < 0) {
                        msg.showMessage("Kembalian Tidak Boleh Minus", "Validasi error", 2);
                    } else {
                        if (bayarView.getMthdCash().isSelected()) {

                            this.cashier.payWithCash(totalPrice, cash, kembalian, this.idOrder);
                            msg.showMessage("Pembayaran berhasil", "Success", 1);
                        } else {
                            String bankName = this.bayarView.getCmbNamaBank().getSelectedItem().toString();
                            this.cashier.payWithBank(totalPrice, bankName, cash, kembalian, this.idOrder);
                            msg.showMessage("Pembayaran berhasil", "Success", 1);
                        }
                    }
                    view.dispose();
                    bayarView.dispose();
                    new OrderController();
                } else {
                    msg.showMessage("Pembayaran Tidak Boleh Huruf", "Validasi error", 2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false) {
                return false;
            }
        }

        return true;
    }
}
