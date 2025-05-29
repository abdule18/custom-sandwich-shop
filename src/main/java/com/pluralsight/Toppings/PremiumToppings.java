package com.pluralsight.Toppings;

import com.pluralsight.Emuns.SandwichSize;

import java.util.Arrays;

public class PremiumToppings {
    private String name;
    private SandwichSize size;
    private double price;
    private boolean extra;

    public static String[] availableMeats = { "Ham", "Turkey", "Steak", "Roast Beef", "Chicken", "Bacon"};
    public static String[] availableCheeses = {"American", "Provolone", "Cheddar", "Swiss"};

    // Use Stream to check if name is in availableMeats array
    public boolean isMeat(){
        //does availableMeats contain the value of name?
        return Arrays.stream(availableMeats).anyMatch(meat ->   meat.equalsIgnoreCase(name));
    }

    // Use Stream to check if name is in availableCheeses array
    public boolean isCheese(){
        //does availableCheese contain the value of name?
        return Arrays.stream(availableCheeses).anyMatch(cheese ->   cheese.equalsIgnoreCase(name));
    }

    public PremiumToppings(String name, SandwichSize size, boolean extra) {
        this.name = name;
        this.size = size;
        this.extra = extra;
        this.price = calculatePrice();
    }

   private double calculatePrice() {
       double basePrice = 0;
       double extraCost = 0;
       // Check if it's cheese using Stream
       if (isMeat()) {
           basePrice = switch (size) {
               case FOUR_INCH -> 1.00;
               case EIGHT_INCH -> 2.00;
               case TWELVE_INCH -> 3.00;
           };

       // Check if it's cheese using Stream
       } else if (isCheese()) {
           basePrice = switch (size) {
               case FOUR_INCH -> 0.75;
               case EIGHT_INCH -> 1.50;
               case TWELVE_INCH -> 2.25;
           };
       }

       // Add extra cost if requested
       if (extra && basePrice > 0) {
           extraCost = switch (size) {
               case FOUR_INCH -> (isMeat() ? 0.50 : 0.30);
               case EIGHT_INCH -> (isMeat() ? 1.00 : 0.60);
               case TWELVE_INCH -> (isMeat() ? 1.50 : 0.90);
           };
           basePrice += extraCost;
       }
       return basePrice;
   }

    public String getName() {
        return name;
    }

    public SandwichSize getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public boolean isExtra() {
        return extra;
    }
}


//       else if(isCheese()) {
//            basePrice = switch (size) {
//                case FOUR_INCH:
//                    price = 0.75;
//                    break;
//                case EIGHT_INCH:
//                    price = 1.50;
//                    break;
//                case TWELVE_INCH:
//                    price = 2.25;
//                    break;
//           };
//            return price;
//
//            //new code here will never run....
//       }