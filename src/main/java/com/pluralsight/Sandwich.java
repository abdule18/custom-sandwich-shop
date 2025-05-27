package com.pluralsight;

public class Sandwich extends Product {
    private String size;
    private String breadType;
    private boolean toasted;

    public Sandwich(String name, double price, String size, String breadType) {
        super(name, price);
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;
    }

    public String getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public double calculatePrice(){
        return getPrice();
    }

    @Override
    public String getDescription(){
        return size + " " + breadType + " " + getName() + (toasted ? " (Toasted)" : "");
    }
}
