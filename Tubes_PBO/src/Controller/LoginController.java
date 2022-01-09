/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.Login;
import Model.Cashier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
/**
 *
 * @author WIBU
 */
public class LoginController extends MouseAdapter implements ActionListener,BaseController{
    
    private Login frame;
    private Cashier kasir;
    
    public LoginController(){
        frame = new Login();
        frame.setTitle("Login Kasir");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(frame.btnlogin())) {
            btnLoginAction();
        }
    }
    
    public void btnLoginAction(){
        String username = frame.getUsername();
        String pass = frame.getPassword();
        if(username.isEmpty()|| pass.isEmpty()){
            msg.showMessage("Username atau Password Kosong","Validasi Eror", 2);
        }else{
            if (username.equalsIgnoreCase("test") && pass.equalsIgnoreCase("12345")) {
                msg.showMessage("Login Successful", "Login", 2);
                new OptionController();
                frame.dispose();
            } else {
                msg.showMessage("Invalid Username or Password", "Validasi Error", 2);
            }
        }
    }
}