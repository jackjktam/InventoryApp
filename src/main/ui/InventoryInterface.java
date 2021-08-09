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

// Provides an interface for the GUI to interact with the Inventory
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

    // MODIFIES: this inv
    // EFFECTS: adds new item with given fields to inventory
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

    // EFFECTS: finds and returns item with given id
    public Item findItem(int id) throws ItemNotFoundException {
        try {
            return inv.findItem(id);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
    }

    // MODIFIES: JSON_STORE
    // EFFECTS: saves the current inventory to static file location
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

    // MODIFIES: this inv
    // EFFECTS: loads the inventory from static file location
    public void load() {
        try {
            inv = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (DuplicateIdException e) {
            System.err.println("Duplicate ID found in file: " + JSON_STORE);
        }
    }

    // MODIFIES: this inv
    // EFFECTS: edits the stock amount of given item
    public void editItemStock(Item item, int amount) throws NegativeAmountException, InsufficientStockException {
        if (amount >= 0) {
            item.increaseStock(amount);
        } else {
            item.decreaseStock(amount);
        }
    }
}
