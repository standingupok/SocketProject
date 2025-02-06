/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package drinkorderingsystemserver;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import utils.CryptoUtil;
import view.FrLogin;
import view.FrManager;

public class DrinkOrderingSystemServer {

    public static void main(String[] args) throws Exception {
        int adminId = -1;
        FrLogin frLogin = new FrLogin();
        frLogin.setVisible(true);

        // Chờ cho đến khi đăng nhập thành công
        while (!frLogin.isAuthenticated()) {
            try {
                Thread.sleep(100); // Đợi một chút để không làm tốn CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        adminId = frLogin.getAdminId();
        FrManager frManageDrinks = new FrManager(adminId);
        frManageDrinks.setVisible(true);
        
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Server is running on port 12345");
                SecretKey secretKey = CryptoUtil.generateKey(); // Tạo khóa AES

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected");

                    // Gửi khóa AES tới client
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(CryptoUtil.keyToString(secretKey));

                    new ServerHandler(socket, secretKey, frManageDrinks).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(DrinkOrderingSystemServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        serverThread.start();
    }
}

