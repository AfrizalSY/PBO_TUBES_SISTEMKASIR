/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;

/**
 *
 * @author WIBU
 */
public class Database {

    static final String DB_URL = "jdbc:mysql://localhost/sistem_kasir_pbo";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public void connectDB() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Select
    public void executeQuery(String query) throws SQLException {
        this.rs = stmt.executeQuery(query);
    }

    // Insert
    public void execute(String query) throws SQLException {
        stmt.execute(query);
    }

    //, Update, delete
    public void executeUpdate(String query) throws SQLException {
        stmt.executeUpdate(query);
    }

    public int executeInsert(String query) throws SQLException {
        stmt = conn.createStatement();
        stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public ResultSet getRs() {
        return this.rs;
    }

    public void disconnectDB() throws SQLException {
        this.conn.close();
    }
}
