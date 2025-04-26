import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;


public class GUI extends JFrame {
    private PlaceholderTextField itemField;
    private JPanel quantityPanel;
    private JLabel quantityLabel;
    private JComboBox<String> outputCombo;
    private JComboBox<String> categoryCombo;
    private JTable displayArea;
    private JScrollPane scrollPane;
    private JButton copyButton, removeButton, totalButton;
    private Button buttonHandler;
    private JCheckBox darkModeToggle;
    private JButton addItemButton;
    private ListOrganizer organizer;
    private JButton clearButton;
    private JButton plus;
    private JButton minus;
    private int[] quantity = new int[]{1}; // starts at 1

    public GUI() {
        // Initialize components
        itemField = new PlaceholderTextField("Enter item");
        outputCombo = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        categoryCombo = new JComboBox<>(new String[]{"Select Category","Produce", "Dairy & Eggs", "Bakery", 
        "Pantry Staples", "Meat & Seafood", "Frozen Food", "Snacks & Beverages", "Household Goods", 
        "Personal Care Items"}); //adds category drop-down menu
        displayArea = new JTable();
        scrollPane = new JScrollPane(displayArea);
        buttonHandler = new Button(organizer, this);
        darkModeToggle = new JCheckBox("Dark Mode");
        addItemButton = new JButton("Add Item");
        organizer = new ListOrganizer();
        clearButton = new JButton("Clear All");
        copyButton = new JButton("Copy");
        removeButton = new JButton("Remove Selected");
        totalButton = new JButton("Total");
        quantityPanel = new JPanel();
        quantityLabel = new JLabel("1");
        plus = new JButton("+");
        minus = new JButton("-");


        // Add customized font and padding
        Font inputFont = new Font("SansSerif", Font.PLAIN, 16);
        itemField.setFont(inputFont);
        outputCombo.setFont(inputFont);
        categoryCombo.setFont(inputFont);

        itemField.setPreferredSize(new Dimension(200, 40));
        outputCombo.setPreferredSize(new Dimension(200, 40));
        categoryCombo.setPreferredSize(new Dimension(200, 40));

        // Set button actions
        copyButton.addActionListener(buttonHandler::copyToClipboard);
        removeButton.addActionListener(e -> {
            int row = displayArea.getSelectedRow();
            if (row >= 0) {
                String item = displayArea.getValueAt(row, 0).toString(); // TODO: adjust column index as needed
                organizer.removeItem(item); // TODO: to implement this
                refreshDisplay();
            }
        });        
        totalButton.addActionListener(buttonHandler::calculateTotal);
        darkModeToggle.addActionListener(e -> {
            boolean isDark = darkModeToggle.isSelected();
            try {
                if (isDark) {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } else {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                }
                FlatLaf.updateUI();
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        });
        addItemButton.addActionListener(e -> {
            String item = itemField.getText();
            String quantity = quantityLabel.getText();
            String category = (String) categoryCombo.getSelectedItem();
            String price = "1.00";
            buttonHandler.addItem(item, price, quantity, category);
            itemField.setText("");
            quantityLabel.setText("1");
        });        
        clearButton.addActionListener(e -> buttonHandler.clearAllLists());
        plus.addActionListener(e -> {
            quantity[0]++;
            quantityLabel.setText(String.valueOf(quantity[0]));
        });
        minus.addActionListener(e -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                quantityLabel.setText(String.valueOf(quantity[0]));
            }
        });

        // Set layout for the JFrame
        setLayout(new BorderLayout());

        // Create input panel and add components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(itemField);
        quantityPanel.setLayout(new FlowLayout());
        quantityPanel.add(minus);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plus);
        inputPanel.add(quantityPanel);
        inputPanel.add(outputCombo);
        inputPanel.add(categoryCombo);
        inputPanel.add(addItemButton);

        // Create button panel and add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(copyButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(darkModeToggle);
        buttonPanel.add(clearButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setTitle("Grocery Price Finder");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    // updates display
    public void refreshDisplay() {
        String[][] tableData = organizer.toTableData(); // get updated data
        String[] columnNames = {"Item", "Quantity", "Category", "Price"};

        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        displayArea.setModel(model); // forces the table to refresh
    }

    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf");
        }
         SwingUtilities.invokeLater(GUI::new);
    }

    
}
