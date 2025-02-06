/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drinkorderingsystemclient;

/**
 *
 * @author ADMIN
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.SecretKey;
import utils.CryptoUtil;

public class ClientHandler implements AutoCloseable {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private SecretKey secretKey;

    public ClientHandler(String host, int port) throws Exception {
        try {
            this.socket = new Socket(host, port);
            this.socket.setSoTimeout(30000); // Timeout 30 giây
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Nhận khóa AES từ server
            String keyString = reader.readLine();
            this.secretKey = CryptoUtil.getKeyFromString(keyString);
        } catch (Exception e) {
            close(); // Đảm bảo đóng tài nguyên nếu lỗi xảy ra trong khởi tạo
            throw e;
        }
        
    }

    public void sendEncryptedCommand(String command, String... args) throws Exception {
        try {
            writer.println(CryptoUtil.encrypt(command, secretKey));
            for (String arg : args) {
                System.out.println(CryptoUtil.encrypt(arg, secretKey));
                writer.println(CryptoUtil.encrypt(arg, secretKey));
            }
        } catch (Exception e) {
            close(); // Đảm bảo đóng tài nguyên nếu lỗi xảy ra trong khởi tạo
            throw e;
        }
    }

    public String readDecryptedResponse() throws Exception {
        try {
            String encryptedResponse = reader.readLine();
            System.out.println(encryptedResponse);
            return CryptoUtil.decrypt(encryptedResponse, secretKey);
        } catch (Exception e) {
            close(); // Đảm bảo đóng tài nguyên nếu lỗi xảy ra trong khởi tạo
            throw e;
        }
    }
    
    public ArrayList<?> getArrayObject() throws IOException, ClassNotFoundException, Exception{
        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {
            String encryptedObject = (String) ois.readObject();
            
            String decryptedObject = CryptoUtil.decrypt(encryptedObject, secretKey);
            
            byte[] serializedData = Base64.getDecoder().decode(decryptedObject);
            System.out.println(serializedData);
            try (ObjectInputStream arrayInputStream = new ObjectInputStream(new ByteArrayInputStream(serializedData))) {
                return (ArrayList<?>) arrayInputStream.readObject();
            }
            
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
            close();
            throw e;
        }
    }
    
    public void sendObject(Object object) throws Exception {
        try {
            // Tuần tự hóa đối tượng và mã hóa
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream objStream = new ObjectOutputStream(bos)) {
                objStream.writeObject(object); // Tuần tự hóa đối tượng
            }
            String encryptedData = CryptoUtil.encrypt(Base64.getEncoder().encodeToString(bos.toByteArray()), secretKey);

            // Gửi dữ liệu đã mã hóa
            writer.println(encryptedData);
        } catch (Exception e) {
            close(); // Đảm bảo đóng tài nguyên nếu lỗi xảy ra trong khởi tạo
            throw e;
        }
    }
    
    public void close() throws IOException {
        try {
            if (socket != null) socket.close();
            if (writer != null) writer.close();
            if (reader != null) reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}

