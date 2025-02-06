/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.dbAccess;
import java.util.ArrayList;
import java.sql.*;
import model.Admin;

/**
 *
 * @author ADMIN
 */
public class AdminDAO implements DAOInterface<Admin>{
    private dbAccess db;

    public AdminDAO(){
        this.db = new dbAccess();
    }

    public static AdminDAO getInstance(){
        return new AdminDAO();
    }
    
    @Override
    public boolean insert(Admin admin) {
        String query = "INSERT INTO Admin (userName, password) VALUES (?, ?)";
        return db.upDate(query, admin.getUserName(), admin.getPassword()) > 0;}

    @Override
    public boolean update(Admin admin) {
        String query = "UPDATE Admin SET password = ? WHERE userName = ?";
        return db.upDate(query, admin.getPassword(), admin.getUserName()) > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Admin WHERE id = ?";
        return db.upDate(query, id) > 0;
    }

    @Override
    public Admin getById(int id) {
        String query = "SELECT * FROM Admin WHERE id = ?";
        ResultSet rs = db.Querry(query, id);
        try {
            if (rs != null && rs.next()) {
                return new Admin(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Admin getByUsername(String username) {
        String query = "SELECT * FROM Admin WHERE username = ?";
        try (ResultSet rs = db.Querry(query, username)) {
            if (rs != null && rs.next()) {
                // Lấy thông tin từ database
                return new Admin(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Admin> getAll() {
        ArrayList<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        ResultSet rs = db.Querry(query);
        try {
            while (rs != null && rs.next()) {
                admins.add(new Admin(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public ArrayList<Admin> getByCondition(String condition, Object... params) {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            String query = "SELECT * FROM Admin WHERE " + condition; // Tên bảng là "Customers"
            ResultSet rs = db.Querry(query, params); // Thực hiện truy vấn với điều kiện và tham số
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("userName");
                String password = rs.getString("password");

                // Tạo đối tượng Customer và thêm vào danh sách
                admins.add(new Admin(id, userName, password));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu cần
        }
        return admins;}
    
}
