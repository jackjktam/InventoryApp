package ui;

import exceptions.ItemNotFoundException;
import model.Item;

import javax.swing.*;

// Helper class for editing inventory
public class EditInventoryManager extends DisplayItem {

    private JTextField idField;
    private JTextField amountField;
    private final InventoryInterface inventoryInterface;

    // EFFECTS: constructs EditInventoryManager and initializes fields
    public EditInventoryManager(InventoryInterface inventoryInterface) {
        this.inventoryInterface = inventoryInterface;
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    private void init() {
        idField = new JTextField("", 4);
        amountField = new JTextField("", 4);
    }

    // EFFECTS: displays search panel
    public void displaySearchPanel() {
        JPanel searchPanel = new JPanel();
        populateSearchField(searchPanel);

        int result = JOptionPane.showConfirmDialog(null, searchPanel, "Enter values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Item item = inventoryInterface.findItem(Integer.parseInt(idField.getText()));
                inventoryInterface.editItemStock(item,
                        Integer.parseInt(amountField.getText()));
                display(item);
            } catch (ItemNotFoundException e) {
                SoundManager.playErrorSound();
                JOptionPane.showMessageDialog(null,"Item not found");
            } catch (Exception e) {
                SoundManager.playErrorSound();
                JOptionPane.showMessageDialog(null, "Please enter valid options");
            }
        }
    }

    // MODIFIES: panel
    // EFFECTS: populates the search panel with labels and fields
    public void populateSearchField(JPanel panel) {
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Amount: "));
        panel.add(amountField);
    }

}
