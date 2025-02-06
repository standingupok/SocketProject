/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ADMIN
 */
public class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
    private final JSpinner spinner;

    public SpinnerEditor() {
        // Tạo spinner mới mỗi lần
        this.spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Số lượng tối thiểu là 1, tối đa là 100
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Gán giá trị ban đầu cho spinner dựa trên giá trị của ô
        spinner.setValue(value);
        return spinner;
    }

    @Override
    public Object getCellEditorValue() {
        // Lấy giá trị hiện tại từ spinner
        return spinner.getValue();
    }
}
