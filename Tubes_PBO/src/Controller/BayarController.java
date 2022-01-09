/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Bayar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author WIBU
 */
public class BayarController implements ActionListener{
    private Bayar frame;
    public BayarController(){
        frame = new Bayar();
        frame.setTitle("Bayar");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(frame.getBtnBayar())) {
        } else if(source.equals(frame.getKembalian())){
        } else if(source.equals(frame.getBtnKembali())){
            new OrderController();
            frame.dispose();
        }
    }
}