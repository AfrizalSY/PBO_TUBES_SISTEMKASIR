///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//import View.Bayar;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JFrame;
//
///**
// *
// * @author WIBU
// */
//public class BayarController implements ActionListener{
//    private Bayar view;
//    public BayarController(){
//        view = new Bayar();
//        view.setTitle("Bayar");
//        view.setVisible(true);
//        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        view.addActionListener(this);
//    }
//    
//    
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        Object source = ae.getSource();
//        if (source.equals(view.getBtnBayar())) {
//        } else if(source.equals(view.getKembalian())){
//        } else if(source.equals(view.getBtnKembali())){
//            new OrderController();
//            view.dispose();
//        }
//    }
//}