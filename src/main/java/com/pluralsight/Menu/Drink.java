package com.pluralsight.Menu;

import com.pluralsight.Emuns.DrinkSize;

public class Drink  extends Product {
    private DrinkSize size;
    private String flavor;

    public Drink(String name, DrinkSize size, String flavor) {
        super(name);
        this.size = size;
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double calculatePrice() {

        return switch (size) {
            case SMALL -> 2;
            case MEDIUM -> 2.50;
            case LARGE -> 3;
        };
    };

    @Override
    public String getDescription() {
        return  size + " " + flavor + " $" + calculatePrice();
    };
}
