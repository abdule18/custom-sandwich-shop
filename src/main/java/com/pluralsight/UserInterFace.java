package com.pluralsight;

public class UserInterFace {
    private static Console console = new Console();


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
        System.out.println("add snadwich in progress");
    }
    public void addDrink(){
        System.out.println("add drink in progress");
    }
    public void addChips(){
        System.out.println("add chips in progress");
    }
    public void checkout(){
        System.out.println("Checkout in progress");
    }
}
