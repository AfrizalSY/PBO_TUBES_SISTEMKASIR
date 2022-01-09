/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author WIBU
 */
public class ItemController implements ActionListener{
    private Item frame;
    public ItemController(){
        frame = new Item();
        frame.setTitle("Login Kasir");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(frame.getbtnKembali())) {
            new OptionController();
            frame.dispose();
        } else if (source.equals(frame.getbtnUpdate())) {
        } else if(source.equals(frame.getbtnTambah())){
        } else if(source.equals(frame.getbtnDelete())){
        }
    }
}