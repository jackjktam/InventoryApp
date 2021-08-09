package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// Manages any action events
public class ActionManager {

    private InventoryInterface inventoryInterface;

    // EFFECTS: creates new action manager with given inventoryapp
    public ActionManager(InventoryInterface inventoryInterface) {
        this.inventoryInterface = inventoryInterface;
    }

    // EFFECTS: handles menu options
    public void handleMenu(JMenuItem menuItem) {
        PersistenceManager persistenceManager = new PersistenceManager(inventoryInterface);
        String text = menuItem.getText();
        if (text.equals("Save inventory")) {
            persistenceManager.save();
        } else if (text.equals("Load inventory from file")) {
            persistenceManager.load();
        }
    }

    // EFFECTS: handles button effects
    public void handleButtons(JButton b) {
        String text = b.getText();
        if (text.equals("Add item")) {
            addItemDialogue();
        } else if (text.equals("List all items")) {
            listItemsDialogue();
        } else if (text.equals("List low stock items")) {
            listLowItemsDialogue();
        } else if (text.equals("Find item")) {
            findItemDialogue();
        } else if (text.equals("Edit inventory")) {
            editInventoryDialogue();
        }
    }

    // MODIFIES: Inventory
    // EFFECTS: displays the inventory editing dialogue
    private void editInventoryDialogue() {
        EditInventoryManager editInventoryManager = new EditInventoryManager(inventoryInterface);
        editInventoryManager.displaySearchPanel();
    }

    // MODIFIES: inventory
    // EFFECTS: displays the item adding dialogue
    private void addItemDialogue() {
        AddItemManager addItemManager = new AddItemManager(inventoryInterface);
        addItemManager.displayAddItemPanel();
    }

    // EFFECTS: displays all items in inventory
    private void listItemsDialogue() {
        ListItemsManager listItemsManager = new ListItemsManager(inventoryInterface.getItemList());
        listItemsManager.displayTable();
    }

    // EFFECTS: displays all items that require restocking
    private void listLowItemsDialogue() {
        ListItemsManager listItemsManager = new ListItemsManager(inventoryInterface.getLowStockItemList());
        listItemsManager.displayTable();
    }

    // EFFECTS: displays the dialogue to find an item
    private void findItemDialogue() {
        FindItemManager findItemManager = new FindItemManager(inventoryInterface);
        findItemManager.displaySearchPanel();
    }
}
