/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author ADMIN
 */
import java.io.IOException;
import java.util.logging.*;

public class LogUtil {
    private static final Logger logger = Logger.getLogger(LogUtil.class.getName());
    
    static {
        try {
            FileHandler fileHandler = new FileHandler("server.log", true); // Ghi log vào file server.log
            fileHandler.setFormatter(new SimpleFormatter()); // Định dạng đơn giản, mỗi log trên một dòng
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }

    public static void severe(String message) {
        logger.severe(message);
    }
}

