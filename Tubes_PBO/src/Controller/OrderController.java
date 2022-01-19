/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author WIBU
 */
public class OrderController implements ActionListener{
    private Order view;
    public OrderController(){
        view = new Order();
        view.setTitle("Option");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}