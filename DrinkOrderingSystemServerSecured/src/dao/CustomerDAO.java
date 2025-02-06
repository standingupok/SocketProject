/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Customer;
import database.dbAccess;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class CustomerDAO implements DAOInterface<Customer> {
    private dbAccess db;

    public CustomerDAO() {
        db = new dbAccess();
    }
    
    public static CustomerDAO getInstance(){
        return new CustomerDAO();
    }

    @Override
    public boolean insert(Customer customer) {
        String query = "INSERT INTO Customers (userName, customerName, email, phoneNumber, customerPassword) VALUES (?, ?, ?, ?, ?)";
        return db.upDate(query, customer.getName(), customer.getUserName(), customer.getEmail(), customer.getPhoneNumber(), customer.getPassword()) > 0;}

    @Override
    public boolean update(Customer customer) {
        String query = "UPDATE Customers SET customerName = ?, email = ?, phoneNumber = ?, customerPassword = ? WHERE userName = ?";
        return db.upDate(query, customer.getName(), customer.getEmail(), customer.getPhoneNumber(), customer.getPassword(), customer.getUserName()) > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Customers WHERE id = ?";
        return db.upDate(query, id) > 0;
    }

    @Override
    public Customer getById(int id) {
        String query = "SELECT * FROM Customers WHERE id = ?";
        ResultSet rs = db.Querry(query, id);
        try {
            if (rs != null && rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("customerName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("customerPassword")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
        // Lấy thông tin người dùng theo username
    public Customer getByUsername(String username) {
        String query = "SELECT * FROM customers WHERE username = ?";
        try (ResultSet rs = db.Querry(query, username)) {
            if (rs != null && rs.next()) {
                // Lấy thông tin từ database
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("customerName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("customerPassword")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customers";
        ResultSet rs = db.Querry(query);
        try {
            while (rs != null && rs.next()) {
                customers.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("customerName"),
                    rs.getString("userName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("customerPassword")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public ArrayList<Customer> getByCondition(String condition, Object... params) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Customers WHERE " + condition; // Tên bảng là "Customers"
            ResultSet rs = db.Querry(query, params); // Thực hiện truy vấn với điều kiện và tham số
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String customerName = rs.getString("customerName");
                String userName = rs.getString("userName"); // Lấy userName từ cột
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String customerPassword = rs.getString("customerPassword");

                // Tạo đối tượng Customer và thêm vào danh sách
                customers.add(new Customer(id, customerName, userName, email, phoneNumber, customerPassword));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu cần
        }
        return customers;
    }

    
}
