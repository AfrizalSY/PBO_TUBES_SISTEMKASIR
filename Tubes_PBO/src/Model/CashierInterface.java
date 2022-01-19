/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;

/**
 *
 * @author WIBU
 */
public interface CashierInterface {

    public void addItem(Food f)throws SQLException;

    public void addItem(Beverage Bf)throws SQLException;

    public void addItem(Item n, int qty, Order o);

    public void editItem(Item n, int VoW) throws SQLException;

    // public void editItem(Item n, Order o);

    public void deleteItem(Item n);

    public void deleteItem(Item n, Order o);
}
