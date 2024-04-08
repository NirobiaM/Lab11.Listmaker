
import java.util.ArrayList;
import java.util.Scanner;

public class Lab_11_Listmaker  {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            System.out.println("Menu:");
            System.out.println("A - Add an item to the list");
            System.out.println("D - Delete an item from the list");
            System.out.println("P - Print the list");
            System.out.println("Q - Quit");

            String choice = getValidMenuChoice();

            switch (choice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit = confirmQuit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addItem() {
        System.out.print("Enter the item to add: ");
        String newItem = scanner.nextLine();
        itemList.add(newItem);
        System.out.println("Item added successfully.");
    }

    private static void deleteItem() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current items:");
        printList();

        int index = getValidIndexToDelete();
        String removedItem = itemList.remove(index);
        System.out.println("Item '" + removedItem + "' removed successfully.");
    }

    private static void printList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current items:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static String getValidMenuChoice() {
        String regex = "[ADPQadpq]";
        return getValidInput("Enter your choice: ", regex);
    }

    private static int getValidIndexToDelete() {
        int maxIndex = itemList.size() - 1;
        String regex = "[0-" + maxIndex + "]";
        return Integer.parseInt(getValidInput("Enter the number of the item to delete: ", regex));
    }

    private static String getValidInput(String prompt, String regex) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        } while (!input.matches(regex));
        return input;
    }

    private static boolean confirmQuit() {
        return getYNConfirm("Are you sure you want to quit? (Y/N): ");
    }

    private static boolean getYNConfirm(String prompt) {
        return getValidInput(prompt, "[YyNn]").equalsIgnoreCase("Y");
    }
}
