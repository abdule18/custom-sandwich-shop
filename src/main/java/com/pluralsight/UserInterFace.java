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
        System.out.println("Home Screen in progress");
    }
    public void showOrderScreen(){
        System.out.println("Oder Screen in progress");
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
