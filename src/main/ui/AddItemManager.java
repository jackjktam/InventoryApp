package ui;

import exceptions.DuplicateIdException;
import exceptions.NegativeAmountException;
import model.Item;

import javax.swing.*;

// Helper class for adding items
public class AddItemManager extends DisplayItem {

    private InventoryInterface inventoryInterface;

    private JTextField idField;
    private JTextField nameField;
    private JTextField stockField;
    private JTextField ropField;

    public AddItemManager(InventoryInterface inventoryInterface) {
        this.inventoryInterface = inventoryInterface;
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    private void init() {
        idField = new JTextField("", 4);
        nameField = new JTextField("", 4);
        stockField = new JTextField("", 4);
        ropField = new JTextField("", 4);
    }

    // MODIFIES: Inventory
    // EFFECTS: displays the add item dialogue
    public void displayAddItemPanel() {

        JPanel panel = new JPanel();
        populateAddItemPanel(panel);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please enter item values", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Item item =
                        inventoryInterface.addItem(Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Integer.parseInt(stockField.getText()),
                        Integer.parseInt(ropField.getText()));
                display(item);
            } catch (NegativeAmountException e) {
                SoundManager.playError();
                JOptionPane.showMessageDialog(null, "Please enter a positive amount");
            } catch (DuplicateIdException e) {
                SoundManager.playError();
                JOptionPane.showMessageDialog(null, "That ID is already in use");
            } catch (Exception e) {
                SoundManager.playError();
                JOptionPane.showMessageDialog(null, "Please enter valid inputs");
            }
        }
    }

    // MODIFIES: panel
    // EFFECTS: adds the fields and labels to the panel
    public void populateAddItemPanel(JPanel panel) {
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("NAME: "));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("STOCK: "));
        panel.add(stockField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("ROP: "));
        panel.add(ropField);
    }
}
