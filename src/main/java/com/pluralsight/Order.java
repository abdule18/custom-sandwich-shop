package com.pluralsight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Sandwich> snadwich;
    private List<Product> products;
    private LocalDateTime orderTime;

    public Order(List<Sandwich> snadwich, List<Product> products, LocalDateTime orderTime) {
        this.snadwich = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orderTime = orderTime;
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
