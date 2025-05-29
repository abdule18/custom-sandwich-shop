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

    public void displayHelper(){
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

    public void showHomeScreen(){
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

    public void showOrderScreen(){
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
    public void addSandwich(){
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


//         Add Cheese Toppings
//        while (true) {
//            String addCheese = console.promptForString("Add a cheese topping? (yes/no): ");
//            if (!addCheese.equalsIgnoreCase("yes")) break;
//
//            String cheeseName = console.promptForString("Enter cheese (American, Provolone, Cheddar, Swiss): ");
//            String extraInput = console.promptForString("Do you want extra cheese? (yes/no): ");
//            boolean isExtra = extraInput.equalsIgnoreCase("yes");
//           // if(validateCheese(cheeseName)){
//                sandwich.addPremiumTopping(cheeseName, isExtra);
//           // }
//           // else{
//           //     System.out.println( "not a valid cheese option");
//           // }
//
//        }

        addCheeseToppings(sandwich);
        addMeatToppings(sandwich);
        addRegularToppings(sandwich);

        System.out.println("Sandwich added  to your order:");
        System.out.println(sandwich.getDescription());
        System.out.println("Total Price: $" + String.format("%.2f", sandwich.calculatePrice()));

        order.addSandwich(sandwich);

    }


    private void addCheeseToppings(Sandwich sandwich) {
        while (true) {
            String addCheese = console.promptForString("Add a cheese topping? (yes/no): ");
            if (!addCheese.equalsIgnoreCase("yes")) break;

            String cheeseName = console.promptForString("Enter cheese (American, Provolone, Cheddar, Swiss): ");
            String extraInput = console.promptForString("Do you want extra cheese? (yes/no): ");
            boolean isExtra = extraInput.equalsIgnoreCase("yes");

            // Add the cheese topping to the sandwich
            sandwich.addPremiumTopping(cheeseName, isExtra);
        }
    }

    private void addMeatToppings(Sandwich sandwich) {
        while (true) {
            String addMeat = console.promptForString("Add a meat topping? (yes/no): ");
            if (!addMeat.equalsIgnoreCase("yes")) break;

            String meatName = console.promptForString("Enter meat (Ham, Turkey, Chicken, etc.): ");
            String extraInput = console.promptForString("Do you want extra meat? (yes/no): ");
            boolean isExtra = extraInput.equalsIgnoreCase("yes");

            // Add the meat topping to the sandwich
            sandwich.addPremiumTopping(meatName, isExtra);
        }
    }

    private void addRegularToppings(Sandwich sandwich) {
        while (true) {
            String addRegular = console.promptForString("Add a regular topping? (yes/no): ");
            if (!addRegular.equalsIgnoreCase("yes")) break;

            String toppingName = console.promptForString("Enter topping name (e.g., Lettuce, Tomato): ");

            // Add the regular topping to the sandwich
            sandwich.addRegularTopping(toppingName);
        }
    }


    public void addDrink(){
        System.out.println("=== Add a Drink ===");
        System.out.println("Choose a drink size: ");
        DrinkSize[]  sizes = DrinkSize.values();
        int index = 1;
        for (DrinkSize size: sizes) {
            System.out.println(index++ + ". " + size);
        }

        int sizeChoice = console.promptForInt("Enter Choice: ");
        DrinkSize selectedSize = sizes[sizeChoice - 1];

        String flavor = console.promptForString("Enter drink flavor (e.g., Coke, Sprite, Orange): ");

        Drink drink = new Drink(flavor + "Drink", selectedSize, flavor);

        order.addProduct(drink);

        System.out.println("Drink added: " + drink.getDescription());
        System.out.println("Price: $" + String.format("%.2f", drink.calculatePrice()));

    }
    public void addChips(){
        System.out.println("=== Add a Chips ===");

        String chipsType  = console.promptForString("Enter chips type  (e.g., Doritos, Lays, BBQ, etc.): ");

        Chips chips = new Chips(chipsType + "Chips", chipsType);

        order.addProduct(chips);

        System.out.println("Chips added: " + chips.getDescription());
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
