/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Drink implements Serializable{
    private int id;
    private String drinkName;
    private double price;
    private int quantity;
    private boolean isAvailable;
    private String imgPath;

    // Constructor
    public Drink(int id, String drinkName, double price, int quantity, boolean isAvailable, String imgPath) {
        super();
        this.id = id;
        this.drinkName = drinkName;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
        this.imgPath = imgPath;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return drinkName; }
    public void setName(String drinkName) { this.drinkName = drinkName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public boolean isAvailable() { return isAvailable; }
    public void setIsAvailable(boolean isAvailable) { this.isAvailable = isAvailable;}
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public String getImgPath() {return imgPath;}
    public void setImgPath(String imgPath) { this.imgPath = imgPath;}
    
}

