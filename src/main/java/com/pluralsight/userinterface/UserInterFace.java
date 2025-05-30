package com.pluralsight.userinterface;

import com.pluralsight.DeliManager.ReceiptManager;
import com.pluralsight.Emuns.BreadType;
import com.pluralsight.Emuns.DrinkSize;
import com.pluralsight.Emuns.SandwichSize;
import com.pluralsight.Menu.Chips;
import com.pluralsight.Menu.Drink;
import com.pluralsight.Menu.Sandwich;
import com.pluralsight.SignatureMenu.SignatureSandwich;
import com.pluralsight.SignatureMenu.SignatureSandwichManager;
import com.pluralsight.Toppings.PremiumToppings;
import com.pluralsight.console.Console;
import com.pluralsight.Menu.Order;

public class UserInterFace {
    private static Console console = new Console();
    private Order order = new Order();
    private SignatureSandwichManager signatureManager = new SignatureSandwichManager();



    public void run(){
        displayHelper();
    }
    private void displayHelper(){
        String promptUser =
                "1. Home Screen\n" +
                        "2. Order Screen\n" +
                        "3. Add a Sandwich\n" +
                        "4. Add a Signature Sandwich\n" +
                        "5. Add a Drink\n" +
                        "6. Add a Chips\n" +
                        "7. Checkout\n" +
                        "0. Exit\n";
        int option;
        do {
            option = console.promptForInt(promptUser);

            switch (option){
                case 1:
                    showHomeScreen();
                    break;
                case 2:
                    showOrderScreen();
                    break;
                case 3:
                    addSandwich();
                    break;
                case 4:
                    addSignatureSandwich();
                    break;
                case 5:
                    addDrink();
                    break;
                case 6:
                    addChips();
                    break;
                case 7:
                    checkout();
                    break;
                case 0:
                    System.out.println("Exiting!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (option != 0);
    }
    private void showHomeScreen(){
        String promptUser =
                "1. New Order\n" +
                "0. Exit\n";

        int userOption;

        do {
            userOption = console.promptForInt(promptUser);
            switch (userOption){
                case 1:
                    showOrderScreen();
                    break;
                case 0:
                    System.out.println("Exiting!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (userOption != 0);

    }
    private void showOrderScreen(){
        String promptUser =
                "1. Add a Sandwich\n" +
                "2. Add a Signature Sandwich\n" +
                "3. Add a Drink\n" +
                "4. Add a Chips\n" +
                "5. Checkout\n" +
                "0. Cancel Order\n";
        int option;
        do {
            option = console.promptForInt(promptUser);

            switch (option){
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addSignatureSandwich();
                    break;
                case 3:
                    addDrink();
                    break;
                case 4:
                    addChips();
                    break;
                case 5:
                    checkout();
                    break;
                case 0:
                    System.out.println("Returning to Home Screen!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (option != 0);
    }
    private void addSandwich(){
        System.out.println("=== Add a Sandwich ===");

        // Choosing Sandwich Size
        System.out.println("Choose a size: ");
        SandwichSize[] sizes = SandwichSize.values();
        int  sizeIndex = 1;
        for (SandwichSize s : sizes) {
            System.out.println(sizeIndex++ + ". " + s);
        }

        int sizeChoice = console.promptForInt("Enter Choice: ");
        SandwichSize size = sizes[sizeChoice - 1];

        // Choosing Bread Type
        System.out.println("Choose a bread type: ");
        BreadType[] breads = BreadType.values();
        int breadIndex = 1;
        for (BreadType b : breads) {
            System.out.println(breadIndex++ + ". " + b);
        }

        int breadChoice = console.promptForInt("Enter Choice: ");
        BreadType bread = breads[breadChoice - 1];

        boolean toasted = console.promptForYesNo("Do you want it toasted? ");

        // Create the sandwich object
        Sandwich sandwich  = new Sandwich("Sandwich", size, bread);
        sandwich.setToasted(toasted);

        addCheeseToppings(sandwich);
        addMeatToppings(sandwich);
        addRegularToppings(sandwich);
        addSides(sandwich);
        addSauces(sandwich);

        System.out.println();
        System.out.println("Sandwich added  to your order:");
        System.out.println(sandwich.getDescription());
        System.out.println("Total Price: $" + String.format("%.2f", sandwich.calculatePrice()));
        System.out.println();

        order.addSandwich(sandwich);

    }
    private void addSignatureSandwich() {
        System.out.println("=== Add a Signature Sandwich ===");

        // Display available signature sandwiches
        String[] signatureOptions = {"BLT", "PHILLY CHEESE STEAK"};
        int index = 1;
        System.out.println("Available Signature Sandwiches:");
        for (String signature : signatureOptions) {
            System.out.println(index++ + ". " + signature);
        }

        int signatureChoice = console.promptForInt("Select a signature sandwich: ");
        if (signatureChoice < 1 || signatureChoice > signatureOptions.length) {
            System.out.println("Invalid choice.");
            return;
        }

        String selectedSignature = signatureOptions[signatureChoice - 1];

        // Choose size
        System.out.println("Choose a size:");
        SandwichSize[] sizes = SandwichSize.values();
        int sizeIndex = 1;
        for (SandwichSize s : sizes) {
            System.out.println(sizeIndex++ + ". " + s);
        }

        int sizeChoice = console.promptForInt("Enter Choice: ");
        if (sizeChoice < 1 || sizeChoice > sizes.length) {
            System.out.println("Invalid size choice.");
            return;
        }
        SandwichSize size = sizes[sizeChoice - 1];

        boolean customize = console.promptForYesNo("Would you like to customize this signature sandwich?");

        SignatureSandwich signatureSandwich = signatureManager.createSignatureSandwich(selectedSignature, size);

        if (customize) {
            Sandwich customSandwich = signatureSandwich.createCustomizableCopy();

            System.out.println("Starting with the base " + selectedSignature + " sandwich...");
            System.out.println("Current toppings:\n" + customSandwich.getFullBreakdown());

            if (console.promptForYesNo("Would you like to add more toppings?")) {
                addCheeseToppings(customSandwich);
                addMeatToppings(customSandwich);
                addRegularToppings(customSandwich);
                addSauces(customSandwich);
            }

            if (console.promptForYesNo("Would you like to remove any existing toppings?")) {
                removeToppingsFromSandwich(customSandwich);
            }

            order.addSandwich(customSandwich);
            System.out.println("✅ Customized " + selectedSignature + " added to your order!");
            System.out.println("Final sandwich:\n" + customSandwich.getFullBreakdown());
            System.out.println("Total Price: $" + String.format("%.2f", customSandwich.calculatePrice()));
        } else {
            order.addSandwich(signatureSandwich);
            System.out.println("✅ " + selectedSignature + " signature sandwich added to your order!");
            System.out.println(signatureSandwich.getFullBreakdown());
            System.out.println("Total Price: $" + String.format("%.2f", signatureSandwich.calculatePrice()));
        }
        System.out.println();
    }
    private void removeToppingsFromSandwich(Sandwich sandwich) {
        System.out.println("=== Remove Toppings ===");
        System.out.println("Current toppings on your sandwich:");

        // Display premium toppings
        if (!sandwich.getPremiumToppings().isEmpty()) {
            System.out.print("Premium toppings: ");
            for (int i = 0; i < sandwich.getPremiumToppings().size(); i++) {
                PremiumToppings topping = sandwich.getPremiumToppings().get(i);
                System.out.print(topping.getName());
                if (topping.isExtra()) {
                    System.out.print(" (Extra)");
                }
                if (i < sandwich.getPremiumToppings().size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        // Display regular toppings
        if (!sandwich.getRegularToppings().isEmpty()) {
            System.out.print("Regular toppings: ");
            for (int i = 0; i < sandwich.getRegularToppings().size(); i++) {
                System.out.print(sandwich.getRegularToppings().get(i).getName());
                if (i < sandwich.getRegularToppings().size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        String toppingToRemove = console.promptForString("Enter the name of the topping to remove (or 'done' to finish): ");
        while (!toppingToRemove.equalsIgnoreCase("done")) {
            if (sandwich.hasPremiumTopping(toppingToRemove)) {
                sandwich.removePremiumTopping(toppingToRemove);
                System.out.println("✅ Removed " + toppingToRemove);
            } else if (sandwich.hasRegularTopping(toppingToRemove)) {
                sandwich.removeRegularTopping(toppingToRemove);
                System.out.println("✅ Removed " + toppingToRemove);
            } else {
                System.out.println("❌ Topping not found: " + toppingToRemove);
            }

            toppingToRemove = console.promptForString("Enter another topping to remove (or 'done' to finish): ");
        }
    }
    private void addCheeseToppings(Sandwich sandwich) {
        if (!console.promptForYesNo("Would you like to add cheese to your sandwich?")) return;

        String[] cheeseOptions = {"American", "Provolone", "Cheddar", "Swiss"};

        boolean done = false;
        while (!done) {
            System.out.println("\n--- Cheese Options ---");
            int index = 1;
            for (String cheese : cheeseOptions) {
                boolean added = false;
                boolean isExtra = false;
                for (PremiumToppings topping : sandwich.getCheeseToppings()) {
                    if (topping.getName().equalsIgnoreCase(cheese)) {
                        added = true;
                        isExtra = topping.isExtra();
                        break;
                    }
                }
                String status = added ? (isExtra ? "[ADDED /w EXTRA]" : "[ADDED]") : "";
                System.out.println(index + ". " + cheese + " " + status);
                index++;
            }
            System.out.println(index + ". Done");

            int choice = console.promptForInt("Select the corresponding number: ");

            if (choice == cheeseOptions.length + 1) {
                done = true;
            } else if (choice >= 1 && choice <= cheeseOptions.length) {
                String selectedCheese = cheeseOptions[choice - 1];
                boolean alreadyAdded = false;
                for (PremiumToppings topping : sandwich.getCheeseToppings()) {
                    if (topping.getName().equalsIgnoreCase(selectedCheese)) {
                        alreadyAdded = true;
                        break;
                    }
                }

                if (alreadyAdded) {
                    System.out.println("❌ You've already added this cheese.");
                    continue;
                }

                boolean isExtra = console.promptForYesNo("Do you want extra " + selectedCheese + "?");
                sandwich.addPremiumTopping(selectedCheese, isExtra);
                System.out.println("✅ Added " + selectedCheese + (isExtra ? " (Extra)" : ""));
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void addMeatToppings(Sandwich sandwich) {
        if (!console.promptForYesNo("Would you like to add Meat Topping to your sandwich?: ")) return;

        String[] meatOptions = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};

        boolean done = false;
        while (!done) {
            System.out.println("\n--- Meat Options ---");
            int index = 1;
            for (String meat : meatOptions) {
                boolean added = false;
                boolean isExtra = false;
                for (PremiumToppings topping : sandwich.getMeatToppings()) {
                    if (topping.getName().equalsIgnoreCase(meat)) {
                        added = true;
                        isExtra = topping.isExtra();
                        break;
                    }
                }
                String status = added ? (isExtra ? "[ADDED /w EXTRA]" : "[ADDED]") : "";
                System.out.println(index + ". " + meat + " " + status);
                index++;
            }
            System.out.println(index + ". Done");

            int choice = console.promptForInt("Select the corresponding number: ");

            if (choice == meatOptions.length + 1) {
                done = true;
            } else if (choice >= 1 && choice <= meatOptions.length) {
                String selectedMeat = meatOptions[choice - 1];
                boolean alreadyAdded = false;
                for (PremiumToppings topping : sandwich.getMeatToppings()) {
                    if (topping.getName().equalsIgnoreCase(selectedMeat)) {
                        alreadyAdded = true;
                        break;
                    }
                }

                if (alreadyAdded) {
                    System.out.println("❌ You've already added this meat.");
                    continue;
                }

                boolean isExtra = console.promptForYesNo("Do you want extra " + selectedMeat + "?");
                sandwich.addPremiumTopping(selectedMeat, isExtra);
                System.out.println("✅ Added " + selectedMeat + (isExtra ? " (Extra)" : ""));
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void addRegularToppings(Sandwich sandwich) {
        if (!console.promptForYesNo("Would you like to add Vegetable Topping to your sandwich?")) return;

        String[] regularOptions = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};

        boolean done = false;
        while (!done) {
            System.out.println("\n--- Regular Topping Options ---");
            int index = 1;
            for (String topping : regularOptions) {
                boolean added = false;
                for (String existing : sandwich.getRegularToppings().stream().map(t -> t.getName()).toList()) {
                    if (existing.equalsIgnoreCase(topping)) {
                        added = true;
                        break;
                    }
                }
                String status = added ? "[ADDED]" : "";
                System.out.println(index + ". " + topping + " " + status);
                index++;
            }
            System.out.println(index + ". Done");

            int choice = console.promptForInt("Select the corresponding number: ");

            if (choice == regularOptions.length + 1) {
                done = true;
            } else if (choice >= 1 && choice <= regularOptions.length) {
                String selectedTopping = regularOptions[choice - 1];
                boolean alreadyAdded = false;
                for (String existing : sandwich.getRegularToppings().stream().map(t -> t.getName()).toList()) {
                    if (existing.equalsIgnoreCase(selectedTopping)) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (alreadyAdded) {
                    System.out.println("❌ You've already added this topping.");
                    continue;
                }
                sandwich.addRegularTopping(selectedTopping);
                System.out.println("✅ Added " + selectedTopping);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void addSauces(Sandwich sandwich) {
        if (!console.promptForYesNo("Would you like to add any sauce?")) return;

        String[] sauceOptions = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};

        boolean done = false;
        while (!done) {
            System.out.println("\n--- Sauce Options ---");
            int index = 1;
            for (String sauce : sauceOptions) {
                boolean added = false;
                for (String existing : sandwich.getRegularToppings().stream().map(t -> t.getName()).toList()) {
                    if (existing.equalsIgnoreCase(sauce)) {
                        added = true;
                        break;
                    }
                }
                String status = added ? "[ADDED]" : "";
                System.out.println(index + ". " + sauce + " " + status);
                index++;
            }
            System.out.println(index + ". Done");

            int choice = console.promptForInt("Select the corresponding number: ");

            if (choice == sauceOptions.length + 1) {
                done = true;
            } else if (choice >= 1 && choice <= sauceOptions.length) {
                String selectedSauce = sauceOptions[choice - 1];
                boolean alreadyAdded = false;
                for (String existing : sandwich.getRegularToppings().stream().map(t -> t.getName()).toList()) {
                    if (existing.equalsIgnoreCase(selectedSauce)) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (alreadyAdded) {
                    System.out.println("❌ You've already added this sauce.");
                    continue;
                }
                sandwich.addRegularTopping(selectedSauce);
                System.out.println("✅ Added " + selectedSauce);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void addDrink(){
        System.out.println("=== Add a Drink ===");
        DrinkSize[] sizes = DrinkSize.values();
        int index = 1;
        System.out.println("\n--- Drink Sizes ---");
        for (DrinkSize size : sizes) {
            System.out.println(index++ + ". " + size);
        }

        int sizeChoice = console.promptForInt("Select drink size: ");
        if (sizeChoice < 1 || sizeChoice > sizes.length) {
            System.out.println("Invalid size choice.");
            return;
        }

        DrinkSize selectedSize = sizes[sizeChoice - 1];

        // Drink flavor options
        String[] flavorOptions = {"Coke", "Sprite", "Fanta", "Orange Juice", "Lemonade"};
        index = 1;
        System.out.println("\n--- Drink Flavors ---");
        for (String flavor : flavorOptions) {
            System.out.println(index++ + ". " + flavor);
        }

        int flavorChoice = console.promptForInt("Select drink flavor: ");
        if (flavorChoice < 1 || flavorChoice > flavorOptions.length) {
            System.out.println("Invalid flavor choice.");
            return;
        }

        String selectedFlavor = flavorOptions[flavorChoice - 1];

        Drink drink = new Drink(selectedFlavor + " Drink", selectedSize, selectedFlavor);
        order.addProduct(drink);
        System.out.println("✅ " + selectedSize + " " + selectedFlavor + " added.");
        System.out.println("Price: $" + String.format("%.2f", drink.calculatePrice()));

    }
    private void addChips(){
        System.out.println("=== Add a Chips ===");

        String[] chipTypes = {"Doritos", "Lays", "Cheetos", "Sun Chips", "BBQ", "Salt & Vinegar"};
        int index = 1;
        System.out.println("\n--- Chips Options ---");
        for (String chip : chipTypes) {
            System.out.println(index++ + ". " + chip);
        }

        int chipChoice = console.promptForInt("Select your chip: ");
        if (chipChoice < 1 || chipChoice > chipTypes.length) {
            System.out.println("Invalid chip choice.");
            return;
        }

        String selectedChip = chipTypes[chipChoice - 1];
        Chips chips = new Chips(selectedChip + " Chips", selectedChip);
        order.addProduct(chips);
        System.out.println("✅ " + selectedChip + " added.");
        System.out.println("Price: $" + String.format("%.2f", chips.calculatePrice()));
    }
    private void addSides(Sandwich sandwich) {
        if (!console.promptForYesNo("Would you like to add sides to your sandwich?")) return;

        String[] sideOptions = {"Au Jus", "Sauce"};

        boolean done = false;
        while (!done) {
            System.out.println("\n--- Side Options ---");
            int index = 1;
            for (String side : sideOptions) {
                boolean added = false;
                for (String existing : sandwich.getRegularToppings().stream().map(t -> t.getName()).toList()) {
                    if (existing.equalsIgnoreCase(side)) {
                        added = true;
                        break;
                    }
                }
                String status = added ? "[ADDED]" : "";
                System.out.println(index + ". " + side + " " + status);
                index++;
            }
            System.out.println(index + ". Done");

            int choice = console.promptForInt("Select the corresponding number: ");

            if (choice == sideOptions.length + 1) {
                done = true;
            } else if (choice >= 1 && choice <= sideOptions.length) {
                String selectedSide = sideOptions[choice - 1];
                boolean alreadyAdded = false;
                for (String existing : sandwich.getRegularToppings().stream().map(t -> t.getName()).toList()) {
                    if (existing.equalsIgnoreCase(selectedSide)) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (alreadyAdded) {
                    System.out.println("❌ You've already added this side.");
                    continue;
                }
                sandwich.addRegularTopping(selectedSide);
                System.out.println("✅ Added " + selectedSide);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void checkout(){
        System.out.println("=== Checking Out ===");

        // Show the full order
        System.out.println(order.getOrderDetails());

        // Ask if user wants to save receipt
        if (console.promptForYesNo("Would you like to confirm this order?")) {
            ReceiptManager manager = new ReceiptManager();
            manager.saveReceipt(order);
            System.out.println("Receipt Saved!");
        }

        System.out.println("Thank you for your order!");
    }
}
