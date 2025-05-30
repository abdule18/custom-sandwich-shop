package com.pluralsight.SignatureMenu;
import com.pluralsight.SignatureMenu.SignatureSandwich;
import com.pluralsight.Emuns.BreadType;
import com.pluralsight.Emuns.SandwichSize;
import java.util.ArrayList;
import java.util.List;

public class SignatureSandwichManager {
    private List<String> availableSignatureSandwiches;

    public SignatureSandwichManager() {
        initializeSignatureSandwiches();
    }

    // Initialize the list of available signature sandwiches

    private void initializeSignatureSandwiches() {
        availableSignatureSandwiches = new ArrayList<>();
        availableSignatureSandwiches.add("BLT");
        availableSignatureSandwiches.add("PHILLY CHEESE STEAK");
    }

    // Get list of all available signature sandwiches

    public List<String> getAvailableSignatureSandwiches() {
        return new ArrayList<>(availableSignatureSandwiches);
    }

    /**
     * Create a signature sandwich with the specified name and size
     * Uses default white bread as specified in the challenge
     */
    public SignatureSandwich createSignatureSandwich(String signatureName, SandwichSize size) {
        if (!availableSignatureSandwiches.contains(signatureName.toUpperCase())) {
            throw new IllegalArgumentException("Unknown signature sandwich: " + signatureName);
        }

        // Default to white bread for signature sandwiches as per challenge document
        return new SignatureSandwich(signatureName, size, BreadType.WHITE);
    }

    // Display signature sandwich menu with descriptions

    public void displaySignatureSandwichMenu() {
        System.out.println("=== Signature Sandwiches ===");
        System.out.println("1. BLT");
        System.out.println("   - White bread, Bacon, Cheddar, Lettuce, Tomato, Ranch (Toasted)");
        System.out.println();
        System.out.println("2. Philly Cheese Steak");
        System.out.println("   - White bread, Steak, American Cheese, Peppers, Mayo (Toasted)");
        System.out.println();
    }

    // Check if a signature sandwich exists

    public boolean isValidSignatureSandwich(String sandwichName) {
        return availableSignatureSandwiches.contains(sandwichName.toUpperCase());
    }

}
