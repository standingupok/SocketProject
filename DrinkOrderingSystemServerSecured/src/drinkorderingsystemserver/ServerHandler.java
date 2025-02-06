/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drinkorderingsystemserver;

/**
 *
 * @author ADMIN
 */
import java.io.*;
import java.net.*;
import dao.CustomerDAO;
import dao.DrinkDAO;
import dao.OrderDAO;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.SecretKey;
import model.Customer;
import model.Drink;
import model.EmailOTP;
import model.Order;
import model.OrderDetail;
import utils.CryptoUtil;
import utils.LogUtil;
import utils.OTPUtil;
import utils.PasswordUtil;
import view.FrManager;

public class ServerHandler extends Thread {
    private Socket socket;
    private SecretKey secretKey;
    private FrManager frManageDrinks;

    public ServerHandler(Socket socket, SecretKey secretKey, FrManager frManageDrinks) {
        this.socket = socket;
        this.secretKey = secretKey;
        this.frManageDrinks = frManageDrinks;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            String encryptedCommand = reader.readLine();
            String command = CryptoUtil.decrypt(encryptedCommand, secretKey); // Giải mã lệnh từ client

            switch (command) {
                case "LOGIN":
                    handleLogin(reader, writer);
                    break;
                    
                case "MENU":
                    handleMenu();
                    break;
                    
                case "ORDER_HISTORY":
                    handleOrderHistory(reader);
                    break;
                    
                case "PLACE_ORDER":
                    handlePlaceOrder(reader);
                    break;
                default:
                    writer.println(CryptoUtil.encrypt("UNKNOWN_COMMAND", secretKey)); // Mã hóa phản hồi
                    throw new AssertionError();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogin(BufferedReader reader, PrintWriter writer) {
        try {
            String encryptedUsername = reader.readLine();
            String encryptedPassword = reader.readLine();

            String username = CryptoUtil.decrypt(encryptedUsername, secretKey); // Giải mã username
            String password = CryptoUtil.decrypt(encryptedPassword, secretKey); 

            LogUtil.info("Attempting login for user: " + username);
            
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getByUsername(username);
            
            
            if (customer == null || !PasswordUtil.verifyPassword(password, customer.getPassword())) {
                LogUtil.warning("Invalid login attempt for user: " + username);
                writer.println(CryptoUtil.encrypt("FAILURE", secretKey));
                writer.println(CryptoUtil.encrypt("Invalid username or password.",secretKey));
            } else {
                LogUtil.info("Login successful for user: " + username);
                writer.println(CryptoUtil.encrypt("SUCCESS",secretKey));
                // Bước 2: Tạo OTP và gửi qua email
                String otp = OTPUtil.generateOTP();
                String email = customer.getEmail();
                
                EmailOTP.sendEmail(email, otp);
                LogUtil.info("OTP sent to email: " + email);
                
                String encryptedclientOTP = reader.readLine();
                String clientOTP = CryptoUtil.decrypt(encryptedclientOTP, secretKey);
                
                if (otp.equals(clientOTP)) {
                    LogUtil.info("OTP verified successfully for user: " + username);
                    String customerId = String.valueOf(customer.getId());
                    
                    writer.println(CryptoUtil.encrypt("OTP_SUCCESS",secretKey));
                    writer.println(CryptoUtil.encrypt(customerId,secretKey));
                    writer.flush();
                } else {
                    LogUtil.warning("Invalid OTP for user: " + username);
                    writer.println("OTP_FAILURE");
                }
            }
            
        } catch (Exception e) {
            LogUtil.severe("Error during login: " + e.getMessage());
            writer.println("ERROR");
            writer.println("System error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void handleMenu(){
        try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            DrinkDAO drinkDAO = new DrinkDAO();
            ArrayList<Drink> drinks = drinkDAO.getAll();
            
            // chuyển drinks thành chuôi json trước khi mã hóa
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream objStream = new ObjectOutputStream(bos)) {
                objStream.writeObject(drinks);
            }
            
            // Mã hóa chuỗi dữ liệu và gửi về client
            String encryptedData = CryptoUtil.encrypt(Base64.getEncoder().encodeToString(bos.toByteArray()), secretKey);
            oos.writeObject(encryptedData);
            
            LogUtil.info("Menu sent successfully to client.");
        } catch (Exception e) {
            LogUtil.severe("Error sending menu: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void handleOrderHistory(BufferedReader reader){
        try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            
            String encryptedCustomerId = reader.readLine();
            String strCustomerId = CryptoUtil.decrypt(encryptedCustomerId, secretKey);
            int customerId = Integer.parseInt(strCustomerId);
            
            LogUtil.info("Fetching order history for customer ID: " + customerId);
            
            OrderDAO orderDAO = new OrderDAO();
            ArrayList<Order> orders = orderDAO.getByCondition("customerId = ?", customerId);
            
            if (orders == null || orders.isEmpty()) {
                LogUtil.warning("No orders found for customer ID: " + customerId);
                orders = new ArrayList<>();
            } else {
                LogUtil.info("Order history retrieved successfully for customer ID: " + customerId);
            }
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream objStream = new ObjectOutputStream(bos)) {
                objStream.writeObject(orders);
            }
            
            // Mã hóa chuỗi dữ liệu và gửi về client
            String encryptedData = CryptoUtil.encrypt(Base64.getEncoder().encodeToString(bos.toByteArray()), secretKey);
            oos.writeObject(encryptedData);
            
            LogUtil.info("Order history sent to client for customer ID: " + customerId);
        } catch (Exception e) {
            LogUtil.severe("Error fetching order history: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    private void handlePlaceOrder(BufferedReader reader) throws Exception{
        try {
            // Đọc dữ liệu mã hóa từ client
            String encryptedOrder = reader.readLine();

            // Giải mã dữ liệu
            String decryptedOrder = CryptoUtil.decrypt(encryptedOrder, secretKey);
            
            byte[] serializedData = Base64.getDecoder().decode(decryptedOrder);
            
            try (ObjectInputStream objStream = new ObjectInputStream(new ByteArrayInputStream(serializedData))) {
                Order newOrder = (Order) objStream.readObject();
                LogUtil.info("Received order from client: " + newOrder);
                
                OrderDAO orderDAO = new OrderDAO();
                boolean result = orderDAO.insert(newOrder);
                
                if (result) {
                    // Giảm số lượng đồ uống dựa trên chi tiết đơn hàng
                    DrinkDAO drinkDAO = new DrinkDAO();
                    for (OrderDetail detail : newOrder.getOrderDetails()) {
                        Drink drink = drinkDAO.getById(detail.getDrinkId());
                        if (drink != null) {
                            int updatedQuantity = drink.getQuantity() - detail.getQuantity();
                            if (updatedQuantity < 1) {
                                LogUtil.warning("Insufficient stock for drink ID: " + detail.getDrinkId());
                                drink.setIsAvailable(false); // Cập nhật trạng thái khả dụng
                            }
                            drink.setQuantity(updatedQuantity);
                            
                            boolean updated = drinkDAO.update(drink);
                            if (!updated) {
                                LogUtil.warning("Failed to update drink stock for drink ID: " + detail.getDrinkId());
                            }
                        } else {
                            LogUtil.warning("Drink not found for ID: " + detail.getDrinkId());
                        }
                    }
                    LogUtil.info("Order saved to database successfully: " + newOrder);
                    // Cập nhật bảng trong giao diện
                    if (frManageDrinks != null) {
                        frManageDrinks.refreshOrderTable();
                    }
                } else {
                    LogUtil.warning("Failed to save order to database: " + newOrder);
                }
            }
            
        } catch (IOException | ClassNotFoundException  e) {
            LogUtil.severe("Error while processing order: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}

