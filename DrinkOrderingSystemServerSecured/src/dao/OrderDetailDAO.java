/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.DAOInterface;
import model.OrderDetail;
import database.dbAccess;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class OrderDetailDAO implements DAOInterface<OrderDetail> {
    private dbAccess db;

    public OrderDetailDAO() {
        this.db = new dbAccess();
    }
    
    @Override
    public boolean insert(OrderDetail orderDetail) {
        if (db == null) {
            System.out.println("Failed to connect to database. Alert from insert OrderDetailDAO.");
            return false;
        }

        String query = "INSERT INTO OrderDetails (orderId, drinkId, quantity) VALUES (?, ?, ?)";
        return db.upDate(query, orderDetail.getOrderId(), orderDetail.getDrinkId(), orderDetail.getQuantity()) > 0;
    }
    
    @Override
    public boolean update(OrderDetail orderDetail) {
        if (db == null) {
            System.out.println("Failed to connect to database. Alert from update OrderDetailDAO.");
            return false;
        }

        String query = "UPDATE OrderDetails SET drinkId = ?, quantity = ? WHERE id = ?";
        return db.upDate(query, orderDetail.getDrinkId(), orderDetail.getQuantity(), orderDetail.getId()) > 0;
    }

    @Override
    public boolean delete(int id) {
        if (db == null) {
            System.out.println("Kết nối cơ sở dữ liệu không tồn tại!");
            return false;
        }

        String query = "DELETE FROM OrderDetails WHERE id = ?";
        return db.upDate(query, id) > 0;
    }

    public ArrayList<OrderDetail> getByOrderId(int orderId) {
        if (db == null) {
            System.out.println("Failed to connect to database. Alert from getByOrderId OrderDetailDAO.");
            return new ArrayList<>();
        }

        String query = "SELECT * FROM OrderDetails WHERE orderId = ?";
        ResultSet rs = db.Querry(query, orderId);
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int drinkId = rs.getInt("drinkId");
                int quantity = rs.getInt("quantity");
                orderDetails.add(new OrderDetail(id, orderId, drinkId, quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public OrderDetail getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OrderDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OrderDetail> getByCondition(String condition, Object...params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

