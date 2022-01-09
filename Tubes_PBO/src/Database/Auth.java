/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.SQLException;

/**
 *
 * @author WIBU
 */
public class Auth extends Database {
    public Boolean checkUsername(String username) throws SQLException{
        boolean cek = false;
        connect();
        String query = "SELECT * FROM kasir WHERE username='"+ username +"'";
        rs = stmt.executeQuery(query);
        if(rs.next()){
            cek = true;
        }
        disconnect();
        return cek;
    }
}
