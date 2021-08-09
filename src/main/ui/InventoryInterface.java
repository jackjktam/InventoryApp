package ui;

import exceptions.DuplicateIdException;
import exceptions.InsufficientStockException;
import exceptions.ItemNotFoundException;
import exceptions.NegativeAmountException;
import model.Inventory;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Code borrowed from tellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
 */
public class InventoryInterface {

    private static final String JSON_STORE = "./data/inventory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Inventory inv;

    public InventoryInterface() {
        init();
    }

    public Inventory getInventory() {
        return inv;
    }

    private void init() {
        inv = new Inventory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    public void addItem(int id, String name, int initStock, int rop)
            throws DuplicateIdException, NegativeAmountException {
        try {
            inv.addItem(id, name, initStock, rop);
        } catch (DuplicateIdException e) {
            throw new DuplicateIdException();
        } catch (NegativeAmountException e) {
            throw new NegativeAmountException();
        }
    }

    private void retrieveItemCount() {
/*        int id;
        System.out.println("Enter item id");
        id = sc.nextInt();
        try {
            System.out.println("item count: " + inv.getItemCount(id));
        } catch (ItemNotFoundException e) {
            System.err.println("Item not found");
        }*/
    }

    private void decreaseItemCount() {
/*        int id;
        int amount;

        System.out.println("Enter item id");
        id = sc.nextInt();
        System.out.println("Enter amount");
        amount = sc.nextInt();

        try {
            inv.decreaseStock(id, amount);
        } catch (ItemNotFoundException e) {
            System.err.println("item not found");
        } catch (NegativeAmountException e) {
            System.err.println("Please enter a positive amount");
            decreaseItemCount();
        } catch (InsufficientStockException e) {
            System.err.println("You cannot decrease the stock by that amount");
        }*/
    }

    public void listAllItems() {
        List<Item> itemList = inv.getItemList();
        System.out.println("\tID\t\tNAME\t\tSTOCK\t\tREORDER POINT");
        for (Item i : itemList) {
            System.out.printf("\t%d\t\t%s\t\t%d\t\t\t%d\n",
                    i.getId(), i.getName(), i.getStock(), i.getReorderPoint());
        }
    }

    private void listReorderItems() {
/*        System.out.println("The items that require re-ordering are: ");
        ArrayList<Item> lsi = inv.getLowStockItems();
        System.out.println("\tID\t\tNAME\t\tSTOCK\t\tREORDER POINT");
        for (Item i : lsi) {
            System.out.printf("\t%d\t\t%s\t\t%d\t\t\t%d\n",
                    i.getId(), i.getName(), i.getStock(), i.getReorderPoint());
        }*/
    }

    private void confirmSaveBeforeQuit() {
 /*       System.out.println("Do you wish to save before quitting? (y/n)");
        String res = sc.next();
        if (res.equals("y")) {
            saveInventory();
            run = false;
        } else if (res.equals("n")) {
            run = false;
        } else {
            System.err.println("Enter a valid choice");
            confirmSaveBeforeQuit();
        }*/
    }

    public Item findItem(int id) throws ItemNotFoundException {
        try {
            return inv.findItem(id);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
    }

    public void save() throws FileNotFoundException {
        try {
            jsonWriter.open();
            jsonWriter.write(inv);
            jsonWriter.close();
            System.out.println("Saved inventory to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public void load() {
        try {
            inv = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (DuplicateIdException e) {
            System.err.println("Duplicate ID found in file: " + JSON_STORE);
        }
    }

    public void editItemStock(Item item, int amount) throws NegativeAmountException, InsufficientStockException {
        if (amount >= 0) {
            item.increaseStock(amount);
        } else {
            item.decreaseStock(amount);
        }
    }
}
