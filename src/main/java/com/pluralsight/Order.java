package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> snadwichs;
    private List<Product> products;
    private LocalDateTime orderTime;
    private String orderId;

    public Order() {
        this.snadwichs = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.orderId = this.orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

    }

    public void addSandwich(Sandwich sandwich) {
        this.snadwichs.add(sandwich);
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public double calculateTotal(){

        double total = 0.0;

        for (Sandwich sandwich : snadwichs) {
            total +=sandwich.calculatePrice();
        }

        for (Product product : products) {
            total +=product.calculatePrice();
        }
        return total;
    }

    public String getOrderDetails(){
        return "";
    }

    public String generateReciept(){
        return "";
    }
}
