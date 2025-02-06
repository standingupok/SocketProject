/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{
    private int id;
    private int customerId;
    private String machineId;
    private String orderStatus;
    private String orderDate;
    private ArrayList<OrderDetail> orderDetails;

    // Constructor
    public Order(int id, int customerId, String machineId, String orderStatus, String orderDate, ArrayList<OrderDetail> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.machineId = machineId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getMachineId() { return machineId; }
    public void setMachineId(String machineId) { this.machineId = machineId; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public ArrayList<OrderDetail> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) { this.orderDetails = orderDetails; }
}

