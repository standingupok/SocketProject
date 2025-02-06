/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Machine {
    private int id;             
    private String machineName;
    private String machineId;

    // Constructor không tham số
    public Machine() {
    }

    // Constructor đầy đủ tham số
    public Machine(int id, String machineName, String machineId) {
        this.id = id;
        this.machineName = machineName;
        this.machineId = machineId;
    }

    // Getter và Setter cho từng thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    // Phương thức hiển thị thông tin máy (tùy chọn)
    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", machineName='" + machineName + '\'' +
                ", machineId='" + machineId + '\'' +
                '}';
    }
}

