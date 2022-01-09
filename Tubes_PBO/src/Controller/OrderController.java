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
    private Order frame;
    public OrderController(){
        frame = new Order();
        frame.setTitle("Option");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(frame.getBtnTambahOrder())) {
        } else if (source.equals(frame.getBtnBayar())) {
            new BayarController();
            frame.dispose();
        } else if(source.equals(frame.getBtnHapus())){
        } else if(source.equals(frame.getBtnKembali())){
            new OptionController();
            frame.dispose();
        }
    }
}