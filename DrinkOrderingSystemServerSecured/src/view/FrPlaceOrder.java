/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.DrinkDAO;
import dao.MachineDAO;
import dao.OrderDAO;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.Customer;
import model.Drink;
import model.Machine;
import model.SpinnerEditor;
import model.Order;
import model.OrderDetail;
/**
 *
 * @author ADMIN
 */
public class FrPlaceOrder extends javax.swing.JFrame {
    private OrderDAO orderDAO;
    private MachineDAO machineDAO;
    private DrinkDAO drinkDAO;
    private Customer customer;
    /**
     * Creates new form FrPlaceOrder
     */
    public FrPlaceOrder() {
        this.machineDAO = new MachineDAO();
        this.drinkDAO = new DrinkDAO();
        this.orderDAO = new OrderDAO();
        initComponents();
        displayDrinks();
    }
    
    public FrPlaceOrder(Customer customer) {
        this.customer = customer;
        this.machineDAO = new MachineDAO();
        this.drinkDAO = new DrinkDAO();
        this.orderDAO = new OrderDAO();
        initComponents();
        displayDrinks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelDrinks = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBtnPlaceOrder = new javax.swing.JButton();
        jTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product name", "Quantity", "Price", "Sub total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanelDrinksLayout = new javax.swing.GroupLayout(jPanelDrinks);
        jPanelDrinks.setLayout(jPanelDrinksLayout);
        jPanelDrinksLayout.setHorizontalGroup(
            jPanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );
        jPanelDrinksLayout.setVerticalGroup(
            jPanelDrinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Total:");

        jBtnPlaceOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBtnPlaceOrder.setText("Order");
        jBtnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPlaceOrderActionPerformed(evt);
            }
        });

        jTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTotal.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelDrinks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnPlaceOrder)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelDrinks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTotal)
                            .addComponent(jBtnPlaceOrder))
                        .addGap(16, 16, 16))))
        );

        jTabbedPane1.addTab("Place an order", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 832, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Order history", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPlaceOrderActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            ArrayList<OrderDetail> orderDetails = new ArrayList<>();

            for (int i = 0; i < model.getRowCount(); i++) {
                int drinkId = (int) model.getValueAt(i, 0);
                int quantity = (int) model.getValueAt(i, 2);
                orderDetails.add(new OrderDetail(0, 0, drinkId, quantity));
            }
            String hostname = InetAddress.getLocalHost().getHostName();

            
            Machine machine = machineDAO.getByMachineId(hostname);
            

            if (machine == null) {
                JOptionPane.showMessageDialog(null, "Cannot find machine with IP: " + hostname);
                return;
            }
            
            // Tạo đơn hàng
            int customerId = customer.getId();
            Order newOrder = new Order(0, customerId, hostname, "Pending", java.time.LocalDate.now().toString(), orderDetails);

            // Thêm đơn hàng vào cơ sở dữ liệu
            boolean result = orderDAO.insert(newOrder);

            if (result) {
                JOptionPane.showMessageDialog(null, "Place order successfully");
                 model.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to place order");
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jBtnPlaceOrderActionPerformed

    private void displayDrinks() {
        ArrayList<Drink> drinks = drinkDAO.getAll(); // Lấy danh sách từ cơ sở dữ liệu

        // Hiển thị các Drink như menu nút
        jPanelDrinks.removeAll();
        jPanelDrinks.setLayout(new GridLayout(0, 3, 10, 10)); // Hiển thị dạng lưới

        for (Drink drink : drinks) {
            JButton drinkButton = new JButton();

            // Đặt hình ảnh vào nút
            ImageIcon icon = new ImageIcon(drink.getImgPath());
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            drinkButton.setIcon(new ImageIcon(img));

            // Đặt tên nút là tên đồ uống
            drinkButton.setText(String.valueOf(drink.getName()));
            drinkButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            drinkButton.setHorizontalTextPosition(SwingConstants.CENTER);

            // Sự kiện khi người dùng nhấn vào nút
            drinkButton.addActionListener(e -> addDrinkToOrder(drink));

            // Thêm nút vào panel
            jPanelDrinks.add(drinkButton);
        }
        jPanelDrinks.revalidate();
        jPanelDrinks.repaint();

        // Gán SpinnerEditor cho cột Quantity
        TableColumn quantityColumn = jTable1.getColumnModel().getColumn(2); // Cột Quantity
        quantityColumn.setCellEditor(new SpinnerEditor()); // Sử dụng editor mới tạo
    }
    
    private void calculateTotal() {
        double total = 0;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Duyệt qua tất cả các hàng trong bảng
        for (int i = 0; i < model.getRowCount(); i++) {
            double subTotal = (double) model.getValueAt(i, 4); // Lấy giá trị cột Sub total
            total += subTotal; // Cộng dồn
        }

        // Hiển thị tổng giá trị trong JLabel jTotal
        jTotal.setText(String.format("%.2f", total));
    }
    
    private void placeOrder() throws UnknownHostException{
        
    }
    
    private void addDrinkToOrder(Drink drink) {
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();

        // Kiểm tra xem đồ uống đã tồn tại trong bảng hay chưa
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = (int) model.getValueAt(i, 0);
//            if(!id)    continue;
            if (id == drink.getId()) {
                // Nếu đã tồn tại, tăng số lượng lên 1
                int quantity = (int) model.getValueAt(i, 2) + 1;
                model.setValueAt(quantity, i, 2);

                // Cập nhật cột thành ti
                double price = (double) model.getValueAt(i, 3);
                double subTotal = price * quantity;
                model.setValueAt(subTotal, i, 4);
                calculateTotal();
                return;
            }
        }

        // Nếu chưa tồn tại, thêm mới
        model.addRow(new Object[]{
            drink.getId(),
            drink.getName(),
            1, // Số lượng mặc định là 1
            drink.getPrice(), // Thành tiền = Giá
            drink.getPrice() // Tổng thành tiền
        });
        calculateTotal();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrPlaceOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnPlaceOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDrinks;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jTotal;
    // End of variables declaration//GEN-END:variables

}
