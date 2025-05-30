package com.pluralsight.Menu;

import com.pluralsight.Emuns.BreadType;
import com.pluralsight.Emuns.SandwichSize;
import com.pluralsight.Toppings.PremiumToppings;
import com.pluralsight.Toppings.RegularToppings;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {

    // === Fields ===
    private SandwichSize size;
    private BreadType breadType;
    private boolean toasted;
    private List<PremiumToppings> premiumToppings;
    private List<RegularToppings> regularToppings;

    // === Constructor ===
    public Sandwich(String name, SandwichSize size, BreadType breadType) {
        super(name);
        this.size = size;
        this.breadType = breadType;
        this.toasted = false;
        this.premiumToppings = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
    }

    // === Base Price ===
    public double getBasePricing() {
        return switch (size) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
        };
    }

    // === Add Toppings ===
    public void addPremiumTopping(String name, boolean extra) {
        premiumToppings.add(new PremiumToppings(name, size, extra));
    }

    public void addRegularTopping(String name) {
        regularToppings.add(new RegularToppings(name));
    }

    // === Remove Toppings ===
    public void removePremiumTopping(String toppingName) {
        premiumToppings.removeIf(topping -> topping.getName().equalsIgnoreCase(toppingName));
    }

    public void removeRegularTopping(String toppingName) {
        regularToppings.removeIf(topping -> topping.getName().equalsIgnoreCase(toppingName));
    }

    // === Check Toppings ===
    public boolean hasPremiumTopping(String toppingName) {
        for (PremiumToppings topping : premiumToppings) {
            if (topping.getName().equalsIgnoreCase(toppingName)) return true;
        }
        return false;
    }

    public boolean hasRegularTopping(String toppingName) {
        for (RegularToppings topping : regularToppings) {
            if (topping.getName().equalsIgnoreCase(toppingName)) return true;
        }
        return false;
    }

    // === Getters ===
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

    public List<PremiumToppings> getPremiumToppings() {
        return new ArrayList<>(premiumToppings);
    }

    public List<RegularToppings> getRegularToppings() {
        return new ArrayList<>(regularToppings);
    }

    public List<PremiumToppings> getMeatToppings() {
        List<PremiumToppings> meats = new ArrayList<>();
        for (PremiumToppings topping : premiumToppings) {
            if (topping.isMeat()) {
                meats.add(topping);
            }
        }
        return meats;
    }

    public List<PremiumToppings> getCheeseToppings() {
        List<PremiumToppings> cheeses = new ArrayList<>();
        for (PremiumToppings topping : premiumToppings) {
            if (topping.isCheese()) {
                cheeses.add(topping);
            }
        }
        return cheeses;
    }

    public double getTotalToppingCount() {
        return premiumToppings.size() + regularToppings.size();
    }

    // === Pricing ===
    public double getPremiumToppingTotal() {
        double total = 0;
        for (PremiumToppings topping : premiumToppings) {
            total += topping.getPrice();
        }
        return total;
    }

    public double getRegularToppingTotal() {
        double total = 0;
        for (RegularToppings topping : regularToppings) {
            total += topping.getPrice();
        }
        return total;
    }

    @Override
    public double calculatePrice() {
        return getBasePricing() + getPremiumToppingTotal() + getRegularToppingTotal();
    }

    // === Descriptions ===
    @Override
    public String getDescription() {
        return getSummaryLine();
    }
    public String getSummaryLine() {
        return size + " " + breadType + " " + getName() + (toasted ? " (Toasted)" : "");
    }

    public String getFullBreakdown() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSummaryLine()).append("\n");

        if (!premiumToppings.isEmpty()) {
            sb.append("Premium Toppings: ");
            for (int i = 0; i < premiumToppings.size(); i++) {
                PremiumToppings topping = premiumToppings.get(i);
                sb.append(topping.getName());
                if (topping.isExtra()) sb.append(" (Extra)");
                if (i < premiumToppings.size() - 1) sb.append(", ");
            }
            sb.append("\n");
        }

        if (!regularToppings.isEmpty()) {
            sb.append("Regular Toppings: ");
            for (int i = 0; i < regularToppings.size(); i++) {
                sb.append(regularToppings.get(i).getName());
                if (i < regularToppings.size() - 1) sb.append(", ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}

