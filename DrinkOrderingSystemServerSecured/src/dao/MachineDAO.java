/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.dbAccess;
import java.sql.*;
import java.util.ArrayList;
import model.Machine;
/**
 *
 * @author ADMIN
 */
public class MachineDAO implements DAOInterface<Machine> {

    private dbAccess db;

    public MachineDAO() {
        db = new dbAccess();
    }

    // Singleton pattern
    public static MachineDAO getInstance() {
        return new MachineDAO();
    }

    @Override
    public boolean insert(Machine machine) {
        String query = "INSERT INTO Machine (machineName, machineID) VALUES (?, ?)";
        return db.upDate(query, machine.getMachineName(), machine.getMachineId()) > 0;
    }

    @Override
    public boolean update(Machine machine) {
        String query = "UPDATE Machine SET machineName = ? WHERE machineID = ?";
        return db.upDate(query, machine.getMachineName(), machine.getMachineId()) > 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Machine WHERE id = ?";
        return db.upDate(query, id) > 0;
    }

    @Override
    public Machine getById(int id) {
        String query = "SELECT * FROM Machine WHERE id = ?";
        ResultSet rs = db.Querry(query, id);
        try {
            if (rs != null && rs.next()) {
                return new Machine(
                    rs.getInt("id"),
                    rs.getString("machineName"),
                    rs.getString("machineID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Machine getByMachineId(String machineId) {
        String query = "SELECT * FROM Machine WHERE machineID = ?";
        try (ResultSet rs = db.Querry(query, machineId)) {
            if (rs != null && rs.next()) {
                return new Machine(
                    rs.getInt("id"),
                    rs.getString("machineName"),
                    rs.getString("machineID")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Machine> getAll() {
        String query = "SELECT * FROM Machine";
        ArrayList<Machine> machines = new ArrayList<>();
        ResultSet rs = db.Querry(query);
        try {
            while (rs != null && rs.next()) {
                machines.add(new Machine(
                    rs.getInt("id"),
                    rs.getString("machineName"),
                    rs.getString("machineID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }

    @Override
    public ArrayList<Machine> getByCondition(String condition, Object...params) {
        String query = "SELECT * FROM Machine WHERE " + condition;
        ArrayList<Machine> machines = new ArrayList<>();
        ResultSet rs = db.Querry(query, params);
        try {
            while (rs != null && rs.next()) {
                machines.add(new Machine(
                    rs.getInt("id"),
                    rs.getString("machineName"),
                    rs.getString("machineID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }

}

