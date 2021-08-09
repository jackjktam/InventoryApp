package model;

import exceptions.DuplicateIdException;
import exceptions.InsufficientStockException;
import exceptions.ItemNotFoundException;
import exceptions.NegativeAmountException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;



/*
Represents the entire inventory
 */
public class Inventory implements Writable {

    private List<Item> itemList;

    // EFFECTS: create a new inventory
    public Inventory() {
        this.itemList = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    // EFFECTS: returns the specified item according to id
    public Item findItem(int id) throws ItemNotFoundException {
        for (Item i : itemList) {
            if (i.getId() == id) {
                return i;
            }
        }
        throw new ItemNotFoundException();
    }

    // EFFECTS: returns the stock of the specified item
    public int getItemCount(int id) throws ItemNotFoundException {
        Item item;
        try {
            item = findItem(id);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
        return item.getStock();
    }

    // MODIFIES: item
    // EFFECTS: decreases the stock of an item by the specified amount
    public void decreaseStock(int id, int amount)
            throws NegativeAmountException, ItemNotFoundException, InsufficientStockException {

        Item item;
        try {
            item = findItem(id);
            item.decreaseStock(amount);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        } catch (InsufficientStockException e) {
            throw new InsufficientStockException();
        }
    }

    // EFFECTS: returns a list of all low stock items
    public ArrayList<Item> getLowStockItems() {
        ArrayList<Item> lowStockItems = new ArrayList<>();
        for (Item i : itemList) {
            if (i.needsRestock()) {
                lowStockItems.add(i);
            }
        }
        return lowStockItems;
    }

    public void addItem(Item item) throws DuplicateIdException {
        if (duplicateId(item.getId())) {
            throw new DuplicateIdException();
        }
        itemList.add(item);
    }

    // MODIFIES: this
    // EFFECTS: creates new item with given id and name then adds to list
    public void addItem(int id, String name) throws DuplicateIdException {
        if (duplicateId(id)) {
            throw new DuplicateIdException();
        }
        itemList.add(new Item(id, name));
    }

    // MODIFIES: this
    // EFFECTS: creates new item with given id, name, and init stock then adds to list
    public void addItem(int id, String name, int initStock) throws DuplicateIdException, NegativeAmountException {
        if (duplicateId(id)) {
            throw new DuplicateIdException();
        }
        if (initStock < 0) {
            throw new NegativeAmountException();
        }
        itemList.add(new Item(id, name, initStock));
    }

    // MODIFIES: this
    // EFFECTS: creates new item with given id, name, init stock, and re order point then adds to list
    public void addItem(int id, String name, int initStock, int rop)
            throws DuplicateIdException, NegativeAmountException {

        if (duplicateId(id)) {
            throw new DuplicateIdException();
        }

        if (initStock < 0 || rop < 0) {
            throw new NegativeAmountException();
        }

        itemList.add(new Item(id, name, initStock, rop));
    }

    // EFFECTS: returns true if the id already exists in the item list
    public boolean duplicateId(int id) {
        for (Item i : itemList) {
            if (i.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemList", itemListToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray itemListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i :itemList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
