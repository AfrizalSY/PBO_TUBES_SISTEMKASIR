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

    public void editItem(Food f) throws SQLException;

    public void editItem(Beverage Bf) throws SQLException;

    public void deleteItem(int idx) throws SQLException;

    public void deleteItem(int itemId, int orderId) throws SQLException;
}
