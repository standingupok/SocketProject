/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.DAOInterface;
import model.Drink;
import database.dbAccess;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class DrinkDAO implements DAOInterface<Drink> {
    
    private dbAccess db;

    public DrinkDAO() {
        db = new dbAccess();
    }

    @Override
    public boolean insert(Drink drink) {
        String query = "INSERT INTO Drinks (drinkName, price, quantity, isAvailable, imgPath) VALUES (?, ?, ?, ?, ?)";
        return db.upDate(query, drink.getName(), drink.getPrice(), drink.getQuantity(), drink.isAvailable(), drink.getImgPath()) > 0;
    }

    @Override
    public boolean update(Drink drink) {
        String query = "UPDATE Drinks SET drinkName = ?, price = ?, quantity = ?, isAvailable = ?, imgPath = ? WHERE id = ?";
        return db.upDate(query, drink.getName(), drink.getPrice(), drink.getQuantity(), drink.isAvailable(), drink.getImgPath(), drink.getId()) > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Drinks WHERE id = ?";
        return db.upDate(query, id) > 0;
    }

    @Override
    public Drink getById(int id) {
        String query = "SELECT * FROM Drinks WHERE id = ?";
        ResultSet rs = db.Querry(query, id);
        try {
            if (rs != null && rs.next()) {
                return new Drink(
                    rs.getInt("id"),
                    rs.getString("drinkName"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imgPath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Drink> getAll() {
        ArrayList<Drink> drinks = new ArrayList<>();
        String query = "SELECT * FROM Drinks";
        ResultSet rs = db.Querry(query);
        try {
            while (rs != null && rs.next()) {
                drinks.add(new Drink(
                    rs.getInt("id"),
                    rs.getString("drinkName"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imgPath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;
    }
    
    @Override
    public ArrayList<Drink> getByCondition(String condition, Object... params) {
        ArrayList<Drink> drinks = new ArrayList<>();
        try {
            String query = "SELECT * FROM Drinks WHERE " + condition; // Tên bảng là "Drinks"
            ResultSet rs = db.Querry(query, params); // Thực hiện truy vấn với điều kiện và tham số
            while (rs != null && rs.next()) {
                int id = rs.getInt("id");
                String drinkName = rs.getString("drinkName");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean isAvailable = rs.getBoolean("isAvailable");
                String imgPath = rs.getString("imgPath");

                // Tạo đối tượng Drink và thêm vào danh sách
                drinks.add(new Drink(id, drinkName, price, quantity, isAvailable, imgPath));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu cần
        }
        return drinks;
    }

    
}
