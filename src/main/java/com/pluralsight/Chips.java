package com.pluralsight;

public class Chips extends Product {
    private String type;

    public Chips(String name, double price, String type) {
        super(name, price);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    };

    @Override
    public String getDescription() {
        return  getName();
    };
}
