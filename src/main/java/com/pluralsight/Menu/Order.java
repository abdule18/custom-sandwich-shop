package com.pluralsight.Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> sandwiches;
    private List<Product> products;
    private LocalDateTime orderTime;
    private String orderId;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.orderId = this.orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public double calculateTotal(){

        double total = 0.0;

        for (Sandwich sandwich : sandwiches) {
            total +=sandwich.calculatePrice();
        }

        for (Product product : products) {
            total +=product.calculatePrice();
        }
        return total;
    }

    public String getOrderDetails(){

        StringBuilder sb = new StringBuilder();

        sb.append("========== DELI ORDER ==========\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Time: ").append(orderTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))).append("\n\n");

        if (!sandwiches.isEmpty()) {
            sb.append("SANDWICHES:\n");
            for (Sandwich s : sandwiches){
                sb.append(s.getFullBreakdown()).append("\n");
                sb.append("  Price: $").append(String.format("%.2f", s.calculatePrice())).append("\n\n");
            }
        }

        if (!products.isEmpty()) {
            sb.append("OTHER ITEMS:\n");
            for (Product p : products){
                sb.append("- ").append(p.getDescription()).append("\n");
                sb.append("  Price: $").append(String.format("%.2f", p.calculatePrice())).append("\n\n");
            }
        }

        sb.append("TOTAL: $").append(String.format("%.2f", calculateTotal())).append("\n");
        sb.append("================================\n");

        return sb.toString();
    }

}
