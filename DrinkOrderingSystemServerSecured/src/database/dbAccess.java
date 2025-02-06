/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class dbAccess {
    private Connection con;

    public dbAccess() {
        try {
            con = DatabaseConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int upDate(String query, Object... params) {
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            setParameters(pstmt, params);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ResultSet Querry(String query, Object... params) {
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            setParameters(pstmt, params);
            return pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setParameters(PreparedStatement pstmt, Object... params) throws Exception {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }
}
