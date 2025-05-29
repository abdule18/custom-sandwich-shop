package com.pluralsight.userinterface;

import com.pluralsight.DeliManager.RecieptManager;
import com.pluralsight.Emuns.BreadType;
import com.pluralsight.Emuns.DrinkSize;
import com.pluralsight.Emuns.SandwichSize;
import com.pluralsight.Menu.Chips;
import com.pluralsight.Menu.Drink;
import com.pluralsight.Menu.Sandwich;
import com.pluralsight.console.Console;
import com.pluralsight.Menu.Order;

public class UserInterFace {
    private static Console console = new Console();
    private Order order = new Order();



    public void run(){
        displayHelper();
    }

    private void displayHelper(){
        String promptUser =
                "1. Home Screen\n" +
                        "2. Order Screen\n" +
                        "3. Add a Sandwich\n" +
                        "4. Add a Drink\n" +
                        "5. Add a Chips\n" +
                        "6. Checkout\n" +
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
                    addDrink();
                    break;
                case 5:
                    addChips();
                    break;
                case 6:
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
                        "2. Add a Drink\n" +
                        "3. Add a Chips\n" +
                        "4. Checkout\n" +
                        "0. Cancel Order\n";
        int option;
        do {
            option = console.promptForInt(promptUser);

            switch (option){
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
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

        String toastedInput = console.promptForString("Do you wanted toasted (yes/no): ");
        boolean toasted  = toastedInput.equalsIgnoreCase("yes");

        // Create the sandwich object
        Sandwich sandwich  = new Sandwich("Sandwich", size, bread);
        sandwich.setToasted(toasted);

        addCheeseToppings(sandwich);
        addMeatToppings(sandwich);
        addRegularToppings(sandwich);
        addSauces(sandwich);

        System.out.println();
        System.out.println("Sandwich added  to your order:");
        System.out.println(sandwich.getDescription());
        System.out.println("Total Price: $" + String.format("%.2f", sandwich.calculatePrice()));
        System.out.println();

        order.addSandwich(sandwich);

    }

    private void addCheeseToppings(Sandwich sandwich) {
        String addCheese = console.promptForString("Would you like to add cheese to your sandwich? (yes/no): ");
        if (!addCheese.equalsIgnoreCase("yes")) {
            return; // Skip cheese section
        }

        String[] cheeseOptions = {"American", "Provolone", "Cheddar", "Swiss"};
        boolean addMore = true;

        while (addMore) {
            System.out.println("\n--- Cheese Options ---");
            int index = 1;
            for (String cheese : cheeseOptions) {
                System.out.println(index++ + ". " + cheese);
            }

            int choice = console.promptForInt("Select the corresponding number: ");
            if (choice >= 1 && choice <= cheeseOptions.length) {
                String selectedCheese = cheeseOptions[choice - 1];
                String extraInput = console.promptForString("Do you want extra " + selectedCheese + "? (yes/no): ");
                boolean isExtra = extraInput.equalsIgnoreCase("yes");
                sandwich.addPremiumTopping(selectedCheese, isExtra);
                System.out.println("Adding " + selectedCheese + (isExtra ? " (Extra)" : ""));
            } else {
                System.out.println("Invalid choice.");
            }

            int more = console.promptForInt("Do you want to add another cheese? (1-Yes, 2-No): ");
            addMore = (more == 1);
        }
    }

    private void addMeatToppings(Sandwich sandwich) {
        String addMeat = console.promptForString("Would you like to add Meat Topping to your sandwich? (yes/no): ");
        if (!addMeat.equalsIgnoreCase("yes")) {
            return; // Skip cheese section
        }
        String[] meatOptions = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
        boolean addMore = true;

        while (addMore) {
            System.out.println("\n--- Meat Options ---");
            int index = 1;
            for (String meat : meatOptions) {
                System.out.println(index++ + ". " + meat);
            }

            int choice = console.promptForInt("Select the corresponding number: ");
            if (choice >= 1 && choice <= meatOptions.length) {
                String selectedMeat = meatOptions[choice - 1];
                String extraInput = console.promptForString("Do you want extra " + selectedMeat + "? (yes/no): ");
                boolean isExtra = extraInput.equalsIgnoreCase("yes");
                sandwich.addPremiumTopping(selectedMeat, isExtra);
                System.out.println("Adding " + selectedMeat + (isExtra ? " (Extra)" : ""));
            } else {
                System.out.println("Invalid choice.");
            }

            int more = console.promptForInt("Do you want to add another meat? (1-Yes, 2-No): ");
            addMore = (more == 1);
        }
    }

    private void addRegularToppings(Sandwich sandwich) {
        String addVegetable = console.promptForString("Would you like to add Vegetable to your sandwich? (yes/no): ");
        if (!addVegetable.equalsIgnoreCase("yes")) {
            return; // Skip cheese section
        }
        String[] regularOptions = {
                "Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños",
                "Cucumbers", "Pickles", "Guacamole", "Mushrooms"
        };

        boolean addMore = true;

        while (addMore) {
            System.out.println("\n--- Regular Topping Options ---");
            int index = 1;
            for (String topping : regularOptions) {
                System.out.println(index++ + ". " + topping);
            }

            int choice = console.promptForInt("Select the corresponding number: ");
            if (choice >= 1 && choice <= regularOptions.length) {
                String selectedTopping = regularOptions[choice - 1];
                sandwich.addRegularTopping(selectedTopping);
                System.out.println("Adding " + selectedTopping);
            } else {
                System.out.println("Invalid choice.");
            }

            int more = console.promptForInt("Do you want to add another topping? (1-Yes, 2-No): ");
            addMore = (more == 1);
        }
    }

    private void addSauces(Sandwich sandwich) {
        String addSauce = console.promptForString("Would you like to add any Sauce? (yes/no): ");
        if (!addSauce.equalsIgnoreCase("yes")) {
            return; // Skip cheese section
        }
        String[] sauceOptions = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};
        boolean addMore = true;

        while (addMore) {
            System.out.println("\n--- Sauce Options ---");
            int index = 1;
            for (String sauce : sauceOptions) {
                System.out.println(index++ + ". " + sauce);
            }

            int choice = console.promptForInt("Select the corresponding number: ");
            if (choice >= 1 && choice <= sauceOptions.length) {
                String selectedSauce = sauceOptions[choice - 1];
                sandwich.addRegularTopping(selectedSauce);
                System.out.println("✅ Adding " + selectedSauce);
            } else {
                System.out.println("Invalid choice.");
            }

            int more = console.promptForInt("Do you want to add another sauce? (1-Yes, 2-No): ");
            addMore = (more == 1);
        }
    }

    public void addDrink(){
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
    public void addChips(){
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
    public void checkout(){
        System.out.println("=== Checking Out ===");

        // Show the full order
        System.out.println(order.getOrderDetails());

        // Ask if user wants to save receipt
        String save = console.promptForString("Would you like to confirm this order? (yes/no): ");
        if (save.equalsIgnoreCase("yes")); {
            RecieptManager manager = new RecieptManager();
            manager.saveReciept(order);
            System.out.println("Receipt Saved!");
        }

        System.out.println("Thank you for your order!");
    }
}
