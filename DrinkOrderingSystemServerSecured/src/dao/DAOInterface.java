/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */
import java.util.ArrayList;

public interface DAOInterface<T> {
    // Thêm mới một bản ghi
    boolean insert(T t);

    // Cập nhật bản ghi
    boolean update(T t);

    // Xóa bản ghi theo ID
    boolean delete(int id);

    // Lấy bản ghi theo ID
    T getById(int id);

    // Lấy tất cả các bản ghi
    ArrayList<T> getAll();
    
    // Lấy tất cả các bản ghi
    ArrayList<T> getByCondition(String condition, Object...params);
}

