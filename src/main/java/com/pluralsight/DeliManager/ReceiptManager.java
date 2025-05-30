package com.pluralsight.DeliManager;

import com.pluralsight.Menu.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    public void saveReceipt(Order order){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = now.format(formatter);
        String fileName = timestamp + ".txt";

        try (FileWriter fw = new FileWriter(fileName, true))  {
            fw.write(order.getOrderDetails());
        } catch (IOException e) {
            System.out.println("Failed to save receipt");
        }
    }
}
