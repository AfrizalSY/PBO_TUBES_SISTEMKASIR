/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Option;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author WIBU
 */
public class OptionController implements ActionListener{
    private Option frame;
    
    public OptionController(){
        frame = new Option();
        frame.setTitle("Option");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(frame.getbtnLogout())) {
            new LoginController();
            frame.dispose();
        } else if (source.equals(frame.getbtnOrder())) {
            new OrderController();
            frame.dispose();
        } else if(source.equals(frame.getbtnShowOrder())){
            new ShowAllOrderController();
        } else if(source.equals(frame.getbtnProduk())){
            new ItemController();
            frame.dispose();
        }
    }
}