/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package database;
/**
 *
 * @author ADMIN
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnector {
    public static Connection getConnection() throws SQLException {
      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/drinkorderdb?user=root&password=1234";
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (Exception e) {     
            JOptionPane.showMessageDialog(null,e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
