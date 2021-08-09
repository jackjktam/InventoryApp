package ui;

import exceptions.DuplicateIdException;
import exceptions.ItemNotFoundException;
import exceptions.NegativeAmountException;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// Manages any action events
public class ActionManager {

    private InventoryInterface inventoryApp;

    // EFFECTS: creates new action manager with given inventoryapp
    public ActionManager(InventoryInterface inventoryApp) {
        this.inventoryApp = inventoryApp;
    }

    // EFFECTS: handles menu options
    public void handleMenu(JMenuItem menuItem) {
        String text = menuItem.getText();
        if (text.equals("Save inventory")) {
            try {
                inventoryApp.save();
                JOptionPane.showMessageDialog(null, "Saved inventory");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Could not save file");
            }
        } else if (text.equals("Load inventory from file")) {
            try {
                inventoryApp.load();
                JOptionPane.showMessageDialog(null, "Loaded file");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not load file");
            }
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
        EditInventoryManager editInventoryManager = new EditInventoryManager();
        JPanel searchPanel = new JPanel();
        editInventoryManager.populateSearchField(searchPanel);

        int result = JOptionPane.showConfirmDialog(null, searchPanel, "Enter values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Item item = inventoryApp.findItem(Integer.parseInt(editInventoryManager.getIdField().getText()));
                inventoryApp.editItemStock(item, Integer.parseInt(editInventoryManager.getAmountField().getText()));
                JPanel resultPanel = new JPanel();
                editInventoryManager.populateResultPanel(resultPanel, item);
                JOptionPane.showMessageDialog(null, resultPanel);
            } catch (ItemNotFoundException e) {
                JOptionPane.showMessageDialog(null,"Item not found");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter valid options");
            }

        }
    }

    // MODIFIES: inventory
    // EFFECTS: displays the item adding dialogue
    private void addItemDialogue() {
        AddItemManager addItemManager = new AddItemManager();

        JPanel panel = new JPanel();
        addItemManager.populateAddItemPanel(panel);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please enter item values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                inventoryApp.addItem(Integer.parseInt(addItemManager.getIdField().getText()),
                        addItemManager.getNameField().getText(),
                        Integer.parseInt(addItemManager.getStockField().getText()),
                        Integer.parseInt(addItemManager.getRopField().getText()));

            } catch (NegativeAmountException e) {
                JOptionPane.showMessageDialog(null, "Please enter a positive amount");
            } catch (DuplicateIdException e) {
                JOptionPane.showMessageDialog(null, "That ID is already in use");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter valid inputs");
            }
        }
    }

    // EFFECTS: displays all items in inventory
    private void listItemsDialogue() {
        ListItemsManager listItemsManager =
                new ListItemsManager((ArrayList<Item>) inventoryApp.getInventory().getItemList());
        JTable table = listItemsManager.createTable();
        JScrollPane pane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, pane);
    }

    // EFFECTS: displays all items that require restocking
    private void listLowItemsDialogue() {
        ListItemsManager listItemsManager =
                new ListItemsManager(inventoryApp.getInventory().getLowStockItems());
        JTable table = listItemsManager.createTable();
        JScrollPane pane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, pane);
    }

    // EFFECTS: displays the dialogue to find an item
    private void findItemDialogue() {

        FindItemManager findItemManager = new FindItemManager();
        
        JPanel searchPanel = new JPanel();
        findItemManager.populateSearchPanel(searchPanel);

        int result = JOptionPane.showConfirmDialog(null, searchPanel,
                "Enter item id", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            
            try {
                
                int id = findItemManager.getSearchIdField();
                Item item = inventoryApp.findItem(id);
                
                JPanel resultPanel = new JPanel();
                findItemManager.populateResultPanel(resultPanel, item);
                JOptionPane.showMessageDialog(null, resultPanel);
                
            } catch (ItemNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Item not found");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Enter valid inputs");
            }
        }
    }
}
