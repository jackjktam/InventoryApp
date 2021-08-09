package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryEditor extends JFrame implements ActionListener {

    public static final int WIDTH = 500;
    public static final int HEIGHT = WIDTH;

    private InventoryInterface inventoryApp;
    private ActionManager actionManager;

    JMenuBar menuBar;

    public InventoryEditor() {
        initializeFields();
        initializeGraphics();
    }

    private void initializeFields() {
        inventoryApp = new InventoryInterface();
        actionManager = new ActionManager(inventoryApp);
        menuBar = new Menu(actionManager).menuBar;
    }

    private void initializeGraphics() {
        setJMenuBar(menuBar);
        setLayout(new BoxLayout(getContentPane(),1));
        addButtonsToPane(this);
        setMinimumSize(new Dimension(WIDTH, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void addButtonsToPane(Container pane) {
        addAButton("Add item", pane);
        addAButton("List all items", pane);
        addAButton("List low stock items", pane);
        addAButton("Find item", pane);
        addAButton("Edit inventory", pane);
    }

    private void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        container.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbutton = (JButton) e.getSource();
        actionManager.handleButtons(jbutton);
    }
}
