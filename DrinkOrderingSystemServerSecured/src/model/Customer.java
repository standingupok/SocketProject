/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Customer {
    private int id;
    private String customerName;
    private String userName; // Thêm trường userName
    private String email;
    private String phoneNumber;
    private String customerPassword;

    // Constructor
    public Customer(int id, String customerName, String userName, String email, String phoneNumber, String customerPassword) {
        super();
        this.id = id;
        this.customerName = customerName;
        this.userName = userName; // Gán giá trị userName
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerPassword = customerPassword;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return customerName; }
    public void setName(String customerName) { this.customerName = customerName; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPassword() { return customerPassword; }
    public void setPassword(String customerPassword) { this.customerPassword = customerPassword; }
}
