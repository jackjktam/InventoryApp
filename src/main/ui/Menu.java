package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Helper class to create GUI menu functionality
public class Menu extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    ActionManager actionManager;

    public Menu(ActionManager actionManager) {
        this.actionManager = actionManager;
        createMenu();
    }

    // MODIFIES: this
    // EFFECTS: creates menu bar with one menu containing menu items
    public void createMenu() {

        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("Save inventory");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load inventory from file");
        menuItem.addActionListener(this);
        menu.add(menuItem);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        actionManager.handleMenu(menuItem);
    }
}
