package ui;

import exceptions.ItemNotFoundException;
import model.Item;

import javax.swing.*;

// Helper class for finding items
public class FindItemManager extends DisplayItem {

    private InventoryInterface inventoryInterface;
    private JTextField searchIdField;

    public FindItemManager(InventoryInterface inventoryInterface) {
        this.inventoryInterface = inventoryInterface;
    }

    // EFFECTS: displays a search panel with id field
    public void displaySearchPanel() {
        int result = JOptionPane.showConfirmDialog(null, createSearchPanel(),
                "Enter item id", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Item item = inventoryInterface.findItem(Integer.parseInt(searchIdField.getText()));
                display(item);
            } catch (ItemNotFoundException e) {
                SoundManager.playError();
                JOptionPane.showMessageDialog(null, "Item not found");
            } catch (Exception e) {
                SoundManager.playError();
                JOptionPane.showMessageDialog(null, "Enter valid inputs");
            }
        }
    }

    // EFFECTS: creates and populates a search panel
    public JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        populateSearchPanel(panel);
        return panel;
    }

    // MODIFIES: panel
    // EFFECTS: populates the search panel with fields and labels
    public void populateSearchPanel(JPanel panel) {
        searchIdField = new JTextField("", 4);
        panel.add(searchIdField);
    }
}
