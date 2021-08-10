package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the main GUI
public class InventoryEditor extends JFrame implements ActionListener {

    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    private ActionManager actionManager;

    public InventoryEditor() {
        initializeFields();
        initializeGraphics();
        loadingScreen();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initializeFields() {
        InventoryInterface inventoryInterface = new InventoryInterface();
        actionManager = new ActionManager(inventoryInterface);
    }

    // MODIFIES: this
    // EFFECTS: initializes the main graphic components of the GUI
    private void initializeGraphics() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new GridLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        pack();
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: displays the splash loading screen
    private void loadingScreen() {

        ImageManager imageManager = new ImageManager();
        JLabel picLabel = new JLabel(new ImageIcon(imageManager.getImage()));
        add(picLabel);
        setVisible(true);

        Timer timer = new Timer(0, e -> {
            remove(picLabel);
            mainMenu();
            repaint();
        });
        timer.setRepeats(false);
        timer.setInitialDelay(2000);
        timer.start();
    }

    // MODIFIES: this
    // Sets up all graphical options
    private void mainMenu() {

        setJMenuBar(new Menu(actionManager).menuBar);
        setLayout(new GridLayout(5, 1));

        addButtonsToPane(this);

        pack();
        setVisible(true);
    }

    // MODIFIES: pane
    // EFFECTS: adds buttons to given pane
    private void addButtonsToPane(Container pane) {
        addAButton("Add item", pane);
        addAButton("List all items", pane);
        addAButton("List low stock items", pane);
        addAButton("Find item", pane);
        addAButton("Edit inventory", pane);
    }

    // MODIFIES: container
    // EFFECTS: creates and adds button to the container
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
