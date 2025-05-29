package com.pluralsight.Menu;

public class Chips extends Product {
    private String type;

    public Chips(String name, String type) {
        super(name);
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
        return 1.50;
    };

    @Override
    public String getDescription() {
        return getName();
    };
}
