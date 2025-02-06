/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class OrderDetail implements Serializable {
    private int id;
    private int orderId;
    private int drinkId;
    private int quantity;

    // Constructor
    public OrderDetail(int id, int orderId, int drinkId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.drinkId = drinkId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getDrinkId() { return drinkId; }
    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

