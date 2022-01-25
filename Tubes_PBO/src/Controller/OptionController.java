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
    private Option view;
    
    public OptionController(){
        view = new Option();
        view.setTitle("Option");
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getbtnLogout())) {
            new LoginController();
            view.dispose();
        } else if (source.equals(view.getbtnOrder())) {
            new OrderController();
            view.dispose();
        } else if(source.equals(view.getbtnShowOrder())){
            new ShowAllOrderController();
            view.dispose();
        } else if(source.equals(view.getbtnProduk())){
            new ItemController();
            view.dispose();
        } 
    }
}