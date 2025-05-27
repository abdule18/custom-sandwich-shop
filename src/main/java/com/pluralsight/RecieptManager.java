package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecieptManager {

    public void saveReciept(Order order){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = now.format(formatter);
        String fileName = timestamp + ".txt";

        try (FileWriter fw = new FileWriter(fileName, true))  {
            fw.write("========== DELI RECEIPT ==========\n");
            fw.write("Date: " + now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) + "\n");
            fw.write("Order #: " + timestamp + "\n");
            fw.write("==================================\n\n");

            if (!order.getSnadwichs().isEmpty()) {
                fw.write("SANDWICHES:\n");
                for (Sandwich sandwich: order.getSnadwichs()) {
                    fw.write("- " + sandwich.getDescription() + "\n");
                    fw.write(" Price: $" + String.format("%.2f", sandwich.calculatePrice()) + "\n\n");
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
