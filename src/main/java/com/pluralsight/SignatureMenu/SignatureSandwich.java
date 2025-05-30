package com.pluralsight.SignatureMenu;

import com.pluralsight.Emuns.BreadType;
import com.pluralsight.Emuns.SandwichSize;
import com.pluralsight.Menu.Sandwich;
import com.pluralsight.Toppings.PremiumToppings;
import com.pluralsight.Toppings.RegularToppings;

public class SignatureSandwich extends Sandwich {
    private String signatureName;

    public SignatureSandwich(String signatureName, SandwichSize size, BreadType breadType) {
        super(signatureName, size, breadType);
        this.signatureName = signatureName;
        setupSignatureToppings();
    }

    /**
     * Sets up the predefined toppings based on the signature sandwich type
     */
    private void setupSignatureToppings() {
        switch (signatureName.toUpperCase()) {
            case "BLT":
                // BLT: 5" white bread, Bacon, Cheddar, Lettuce, Tomato, Ranch, Toasted
                addPremiumTopping("Bacon", false);
                addPremiumTopping("Cheddar", false);
                addRegularTopping("Lettuce");
                addRegularTopping("Tomatoes");
                addRegularTopping("Ranch");
                setToasted(true);
                break;

            case "PHILLY CHEESE STEAK":
                // Philly Cheese Steak: 5" white bread, Steak, American Cheese, Peppers, Mayo, Toasted
                addPremiumTopping("Steak", false);
                addPremiumTopping("American", false);
                addRegularTopping("Peppers");
                addRegularTopping("Mayo");
                setToasted(true);
                break;

            default:
                // Handle custom signature sandwiches or throw exception
                System.out.println("Unknown signature sandwich: " + signatureName);
                break;
        }
    }

    public String getSignatureName() {
        return signatureName;
    }

    @Override
    public String getDescription() {
        return "Signature " + signatureName + " - " + super.getDescription();
    }

    /**
     * Creates a customizable copy of this signature sandwich
     * Useful when customer wants to modify a signature sandwich
     */
    public Sandwich createCustomizableCopy() {
        Sandwich copy = new Sandwich(this.getName(), this.getSize(), this.getBreadType());
        copy.setToasted(this.isToasted());

        // Copy all premium toppings
        for (PremiumToppings topping : this.getPremiumToppings()) {
            copy.addPremiumTopping(topping.getName(), topping.isExtra());
        }

        // Copy all regular toppings
        for (RegularToppings topping : this.getRegularToppings()) {
            copy.addRegularTopping(topping.getName());
        }

        return copy;
    }
}
