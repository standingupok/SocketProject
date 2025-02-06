/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.DAOInterface;
import dao.OrderDetailDAO;
import model.Order;
import model.OrderDetail;
import database.dbAccess;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class OrderDAO implements DAOInterface<Order> {
    private dbAccess db;

    public OrderDAO() {
        this.db = new dbAccess();
    }
    
    @Override
    public boolean insert(Order order) {
        if (db == null) {
            System.out.println("Failed to connect to database");
            return false;
        }

        String query = "INSERT INTO Orders (customerId, machineId, orderStatus, orderDate) VALUES (?, ?, ?, ?)";
        boolean result = db.upDate(query, order.getCustomerId(), order.getMachineId(), order.getOrderStatus(), order.getOrderDate()) > 0;
        
        if (result) {
            try {
                ResultSet rs = db.Querry("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    int orderId = rs.getInt(1); // Lấy ID tự động tăng của order
                    System.out.println("Order has been plcaed with ID: " + orderId);

                    // Thêm OrderDetails
                    for (OrderDetail detail : order.getOrderDetails()) {
                        detail.setOrderId(orderId); // Gán orderId cho từng OrderDetail
                        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                        if (!orderDetailDAO.insert(detail)) {
                            System.out.println("Fail to add items");
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return result;
    }

    
    @Override
    public boolean update(Order order) {
        String query = "UPDATE Orders SET customerId = ?, machineId = ?, orderStatus = ?, orderDate = ? WHERE id = ?";
        return db.upDate(query, order.getCustomerId(), order.getMachineId(), order.getOrderStatus(), order.getOrderDate(), order.getId()) > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Orders WHERE id = ?";
        return db.upDate(query, id) > 0;
    }

    public ArrayList<Order> getAll() {
        if (db == null) {
            System.out.println("Kết nối cơ sở dữ liệu không tồn tại!");
            return new ArrayList<>();
        }

        String query = "SELECT * FROM Orders";
        ResultSet rs = db.Querry(query);
        ArrayList<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                String machineId = rs.getString("machineId");
                String orderStatus = rs.getString("orderStatus");
                String orderDate = rs.getString("orderDate");

                // Lấy danh sách chi tiết đơn hàng
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = orderDetailDAO.getByOrderId(id);

                orders.add(new Order(id, customerId, machineId, orderStatus, orderDate, orderDetails));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Order> getByCondition(String condition, Object...params) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM Orders WHERE " + condition;
            ResultSet rs = db.Querry(query, params);
            while(rs != null && rs.next()){
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                String machineId = rs.getString("machineId");
                String orderStatus = rs.getString("orderStatus");
                String orderDate = rs.getString("orderDate");
                
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = orderDetailDAO.getByOrderId(id);
                
                orders.add(new Order(id, customerId, machineId, orderStatus, orderDate, orderDetails));
            }
        } catch (Exception e) {
        }
        return orders;
    }
}

