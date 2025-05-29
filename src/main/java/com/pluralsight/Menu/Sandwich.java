package com.pluralsight.Menu;

import com.pluralsight.Emuns.BreadType;
import com.pluralsight.Emuns.SandwichSize;
import com.pluralsight.Toppings.PremiumToppings;
import com.pluralsight.Toppings.RegularToppings;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    private SandwichSize size;
    private BreadType breadType;
    private boolean toasted;
    private List<PremiumToppings> premiumToppings;
    private List<RegularToppings> regularToppings;

    public Sandwich(String name, SandwichSize size, BreadType breadType) {
        super(name);
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;
        this.premiumToppings = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
    }

    // Returns base price based on sandwich size
    public double getBasePricing(){
        return switch (size) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
        };
    }

    // Adds a premium topping (e.g., meat, cheese
    public void addPremiumTopping(String name, boolean extra) {
        PremiumToppings toppings = new PremiumToppings(name, size, extra);
        premiumToppings.add(toppings);
    }

    // Adds a regular topping (e.g., lettuce, tomato)
    public void addRegularTopping(String name) {
        RegularToppings toppings = new RegularToppings(name);
        regularToppings.add(toppings);
    }

    // Calculates the total cost of premium toppings
    public double getPremiumToppingTotal() {
        double total = 0;
        for (PremiumToppings toppings: premiumToppings) {
            total += toppings.getPrice();
        }
        return total;
    }

    // Calculates the total cost of regular toppings (usually $0)
    public double getRegularToppingTotal() {
        double total = 0;
        for (RegularToppings  toppings : regularToppings) {
            total += toppings.getPrice();
        }
        return total;
    }
    // Returns a list of only meat-type premium toppings
    public List<PremiumToppings> getMeatToppings() {
        List<PremiumToppings> meats = new ArrayList<>();
        for (PremiumToppings toppings : premiumToppings) {
            if (toppings.isMeat()){
                meats.add(toppings);
            }
        }
        return meats;
    }

    // Returns a list of only cheese-type premium toppings
    public List<PremiumToppings> getCheeseToppings() {
        List<PremiumToppings> cheeses = new ArrayList<>();
        for (PremiumToppings toppings : premiumToppings){
            if (toppings.isCheese()){
                cheeses.add(toppings);
            }
        }
        return cheeses;
    }

    public SandwichSize getSize() {
        return size;
    }


    public BreadType getBreadType() {
        return breadType;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public double getTotalToppingCount() {
        return premiumToppings.size() + regularToppings.size();
    }

    @Override
    public double calculatePrice(){
        return getBasePricing() + getPremiumToppingTotal() + getRegularToppingTotal();
    }

    @Override
    public String getDescription(){
        return size + " " + breadType + " " + getName() + (toasted ? " (Toasted)" : "");
    }
}
