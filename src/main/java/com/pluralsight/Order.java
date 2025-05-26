package com.pluralsight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> snadwich;
    private List<Product> products;
    private LocalDateTime orderTime;
    private String orderId;

    public Order(List<Sandwich> snadwich, List<Product> products, LocalDateTime orderTime, String orderId) {
        this.snadwich = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orderTime = orderTime;
        this.orderId = orderId;

    }

    public ArrayList<Sandwich> addSandwich(Sandwich sandwich) {
        return null;
    }

    public ArrayList<Product> addProduct(Product product){
        return null;
    }

    public double calculateTotal(){
        return 0;
    }

    public String getOrderDetails(){
        return "";
    }

    public String generateReciept(){
        return "";
    }
}
