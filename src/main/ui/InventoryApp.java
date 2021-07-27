package ui;

import exceptions.DuplicateIdException;
import exceptions.InsufficientStockException;
import exceptions.ItemNotFoundException;
import exceptions.NegativeAmountException;
import model.Inventory;
import model.Item;

import java.util.ArrayList;
import java.util.Scanner;

/*
Code borrowed from tellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
 */
public class InventoryApp {
    Scanner sc;
    Inventory inv;
    String command = null;
    boolean run;

    public InventoryApp() {
        runApp();
    }

    private void runApp() {

        init();
        System.out.println("Welcome to the inventory management system");
        while (run) {
            displayMenu();
            command = sc.next();
            command = command.toLowerCase();
            if (command == "5") {
                break;
            } else {
                processCommand(command);
            }
        }
    }

    private void processCommand(String command) {
        switch (command) {
            case "1":
                addItem();
                break;
            case "2":
                retrieveItemCount();
                break;
            case "3":
                decreaseItemCount();
                break;
            case "4":
                listReorderProducts();
                break;
            default:
                System.out.println("Enter a valid choice");
        }
    }

    private void init() {
        inv = new Inventory();
        sc = new Scanner(System.in);
        run = true;
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add item");
        System.out.println("\t2 -> Retrieve item count");
        System.out.println("\t3 -> Decrease item count");
        System.out.println("\t4 -> List products that require re-ordering");
        System.out.println("\t5 -> quit");
    }

    private void addItem() {
        int id;
        String name;
        int initStock;
        int rop;

        System.out.println("Enter item id: ");
        id = sc.nextInt();
        System.out.println("Enter product name: ");
        name = sc.next();
        System.out.println("Enter initial stock: ");
        initStock = sc.nextInt();
        System.out.println("Enter re-order point: ");
        rop = sc.nextInt();

        try {
            inv.addItem(id, name, initStock, rop);
        } catch (DuplicateIdException e) {
            System.err.println("That ID has already been used");
            addItem();
        } catch (NegativeAmountException e) {
            System.err.println("The amount you entered must be positive");
            addItem();
        }
    }

    private void retrieveItemCount() {
        int id;
        System.out.println("Enter item id");
        id = sc.nextInt();
        try {
            inv.getItemCount(id);
        } catch (ItemNotFoundException e) {
            System.err.println("Item not found");
            retrieveItemCount();
        }
    }

    private void decreaseItemCount() {
        int id;
        int amount;

        System.out.println("Enter item id");
        id = sc.nextInt();
        System.out.println("Enter amount");
        amount = sc.nextInt();

        try {
            inv.decreaseStock(id, amount);
        } catch (ItemNotFoundException e) {
            System.err.println("item not found");
            decreaseItemCount();
        } catch (NegativeAmountException e) {
            System.err.println("Please enter a positive amount");
            decreaseItemCount();
        } catch (InsufficientStockException e) {
            System.err.println("You cannot decrease the stock by that amount");
            decreaseItemCount();
        }
    }

    private void listReorderProducts() {
        System.out.println("The items that require re-ordering are: ");
        ArrayList<Item> lsi = inv.getLowStockItems();
        for (Item i : lsi) {
            System.out.printf("\t id: %s \t name: %s \t stock: %d \t rop: %d",
                    i.getId(), i.getName(), i.getStock(), i.getReorderPoint());
        }
    }
}
