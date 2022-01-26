/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Login;
import Database.Database;
import Model.Cashier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
// import java.util.logging.Level;
// import java.util.logging.Logger;

/**
 *
 * @author WIBU
 */
public class LoginController extends MouseAdapter implements ActionListener, BaseController {

    private Login view;
    private Database db;
    private Cashier cashier = new Cashier();
    public LoginController() {
        this.view = new Login();
        this.db = new Database();
        view.setTitle("Login Kasir");
        this.view.resetForm();
        view.setVisible(true);
        view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.btnlogin())) {
            doLogin();
        }
    }

    public void doLogin(){
        try{
            String username = this.view.getUsername();
            String pass = this.view.getPassword();
            db.connectDB();
            String sql = "SELECT * FROM kasir WHERE username = '%s' and password = '%s'";
            sql = String.format(sql, username, pass);
            db.executeQuery(sql);
            if(!db.getRs().next()){
                msg.showMessage("Username/Password Salah","GAGAL LOGIN",2);
            }else{
                while(db.getRs().next()){
                    cashier.setName(db.getRs().getString("name"));
                    cashier.setUsername(db.getRs().getString("username"));
                }
                new OptionController();
                view.dispose();
            }
            db.disconnectDB();
        }catch(SQLException e){
            msg.showMessage("Terhadi Kesalahan", "GAGAL LOGIN",0);
        }
    }
}
