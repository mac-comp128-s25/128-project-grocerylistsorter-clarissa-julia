import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.Vector;
import com.formdev.flatlaf.FlatLightLaf;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class GUI extends JFrame {
    private PlaceholderTextField itemField;
    private JLabel quantityLabel;
    private JComboBox<String> outputCombo;
    private JComboBox<String> categoryCombo;
    private JTable displayArea;
    private JScrollPane scrollPane;
    private JButton copyButton, removeButton, searchButton;
    private Button buttonHandler;
    private JButton addItemButton;
    private ListOrganizer organizer;
    private JButton clearButton;
    private JButton plus;
    private JButton minus;
    private int[] quantity = new int[] { 1 }; 
    private Map<String, Double> optionList;
    private JLabel totalLabel;

    public GUI() {
        // Initialize components
        organizer = new ListOrganizer();
        itemField = new PlaceholderTextField("Enter item");
        outputCombo = new JComboBox<>(); // 
        categoryCombo = new JComboBox<>(new String[] { "Select Category", "Produce", "Dairy & Eggs", "Bakery",
            "Pantry Staples", "Meat & Seafood", "Frozen Food", "Snacks & Beverages", "Household Goods",
            "Personal Care Items" });
        displayArea = new JTable();
        scrollPane = new JScrollPane(displayArea);
        buttonHandler = new Button(organizer, this);
        addItemButton = new JButton("Add Item");
        clearButton = new JButton("Clear All");
        copyButton = new JButton("Copy");
        removeButton = new JButton("Remove Selected");
        searchButton = new JButton("Search");
        quantityLabel = new JLabel("1");
        plus = new JButton("+");
        minus = new JButton("-");
        totalLabel = new JLabel("Total: $0.00");

        // Add customized font and padding
        Font inputFont = new Font("SansSerif", Font.PLAIN, 16);
        itemField.setFont(inputFont);
        outputCombo.setFont(inputFont);
        categoryCombo.setFont(inputFont);

        itemField.setPreferredSize(new Dimension(200, 40));
        outputCombo.setPreferredSize(new Dimension(200, 40));
        categoryCombo.setPreferredSize(new Dimension(200, 40));

        // Style Buttons:
        addItemButton.putClientProperty("JButton.buttonType", "roundRect");
        addItemButton.setBackground(new Color(255, 250, 227));
        addItemButton.setForeground(new Color(1,50,32));

        clearButton.putClientProperty("JButton.buttonType", "roundRect");
        clearButton.setBackground(new Color(255, 250, 227));
        clearButton.setForeground(new Color(1,50,32));

        copyButton.putClientProperty("JButton.buttonType", "roundRect");
        copyButton.setBackground(new Color(255, 250, 227));
        copyButton.setForeground(new Color(1,50,32));

        removeButton.putClientProperty("JButton.buttonType", "roundRect");
        removeButton.setBackground(new Color(255, 250, 227));
        removeButton.setForeground(new Color(1,50,32));

        plus.putClientProperty("JButton.buttonType", "roundRect");
        plus.setBackground(new Color(255, 250, 227));
        plus.setForeground(new Color(1,50,32));

        minus.putClientProperty("JButton.buttonType", "roundRect");
        minus.setBackground(new Color(255, 250, 227));
        minus.setForeground(new Color(1,50,32));

        searchButton.putClientProperty("JButton.buttonType", "roundRect");
        searchButton.setBackground(new Color(255, 250, 227));
        searchButton.setForeground(new Color(1,50,32));

        // Styling table
        displayArea.setFillsViewportHeight(true);
        displayArea.setRowHeight(30);
        displayArea.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        displayArea.setSelectionBackground(new Color(188, 200, 138));
        displayArea.setSelectionForeground(Color.BLACK);
        displayArea.setBackground(new Color(255,250,227));

        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        // customize item field
        itemField.setBackground(new Color(255,250,227));

        // Set button actions
        copyButton.addActionListener(e -> buttonHandler.copyToClipboard(e));

        removeButton.addActionListener(e -> {
            int row = displayArea.getSelectedRow();
            if (row >= 0) {
                String item = displayArea.getValueAt(row, 0).toString(); 
                String stringPrice = displayArea.getValueAt(row, 3).toString();
                stringPrice = stringPrice.substring(1);
                double price = Double.parseDouble(stringPrice); 
                String category = displayArea.getValueAt(row, 2).toString();
                organizer.removeItem(item, price, category); 
                refreshDisplay();
            }
        });

        searchButton.addActionListener(e -> {
            String itemName = itemField.getText().trim();
        
            // Create a modal loading dialog
            JDialog loadingDialog = new JDialog(this, "Searching...", true);
            JLabel loadingLabel = new JLabel("Please wait while we search for " + itemName + "...");
            loadingLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            loadingDialog.add(loadingLabel);
            loadingDialog.pack();
            loadingDialog.setLocationRelativeTo(this);
        
            // Create a background worker to perform the API call
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    HttpResponse<JsonNode> response = Unirest.get("https://api-to-find-grocery-prices.p.rapidapi.com/amazon?query=" + itemName)
                        .header("x-rapidapi-key", "52616f87aamsh0800cd10f770123p1199acjsnba1b79044cb2")
                        .header("x-rapidapi-host", "api-to-find-grocery-prices.p.rapidapi.com")
                        .asJson();
        
                    String itemList = response.getBody().toPrettyString();
                    optionList = OptionList.getOptionList(itemList);
                    return null;
                }
        
                @Override
                protected void done() {
                    loadingDialog.dispose(); // Close the loading dialog
                    outputCombo.setModel(new DefaultComboBoxModel<>(new Vector<>(OptionList.getOptionVector(optionList))));
                }
            };
        
            // Start worker and show dialog
            worker.execute();
            loadingDialog.setVisible(true);
        });
       addItemButton.addActionListener(e -> {
    String item = (String) outputCombo.getSelectedItem();
    int quantity = Integer.parseInt(quantityLabel.getText());
    String category = (String) categoryCombo.getSelectedItem();

    if (category.equals("Select Category")) {
        JOptionPane.showMessageDialog(this,
            "Please select a valid category before adding the item.",
            "Missing Category",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (item == null || item.isEmpty() || !optionList.containsKey(item)) {
        JOptionPane.showMessageDialog(this,
            "Please select a valid item from the dropdown before adding.",
            "Invalid Item",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    double price = optionList.get(item);
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

        // create horizontal panel for quanity/serach to be next to each other
        JPanel quantitySearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,0));
        quantitySearchPanel.add(minus);
        quantitySearchPanel.add(quantityLabel);
        quantitySearchPanel.add(plus);
        quantitySearchPanel.add(searchButton);
        quantitySearchPanel.setBackground(new Color(138,154,91));

        // Create input panel and add components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(itemField);
        // quantityPanel.setLayout(new FlowLayout());
        inputPanel.add(quantitySearchPanel);
        // inputPanel.add(quantityPanel);
        inputPanel.add(outputCombo);
        inputPanel.add(categoryCombo);
        inputPanel.add(addItemButton);
        inputPanel.setBackground(new Color(138,154,91));

        // Create button panel and add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(copyButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.setBackground(new Color(138,154,91));


        // create bottom total panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bottomPanel.add(totalLabel, BorderLayout.EAST);
        bottomPanel.setBackground(new Color(138,154,91));

        // Add panels to the frame
        add(inputPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Set frame properties
        setTitle("Grocery Price Finder");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // // updates display
    public void refreshDisplay() {
        String[][] tableData = organizer.toTableData(); // get updated data
        String[] columnNames = { "Item", "Quantity", "Category", "Price" };
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        displayArea.setModel(model); // forces the table to refresh

        //resize columns
        displayArea.getColumnModel().getColumn(0).setPreferredWidth(300);
        displayArea.getColumnModel().getColumn(1).setPreferredWidth(60);
        displayArea.getColumnModel().getColumn(2).setPreferredWidth(150);
        displayArea.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        double total = organizer.totalCalculator();
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    public static void main(String[] args) {
        try {
            UIManager.put("Component.arc", 20);
            UIManager.put("Button.arc", 20);
            UIManager.put("TextComponent.arc", 20);
            UIManager.put("Table.arc", 20);
            UIManager.put("ScrollPane.arc", 20);
            UIManager.put("TabbedPane.tabArc", 20);
            UIManager.put("CheckBox.arc", 20);
            UIManager.put("ComboBox.arc", 20);
            UIManager.put("ComboBox.background", new Color(255, 250, 227));
            UIManager.put("PopupMenu.borderArc", 20);
            UIManager.put("Component.innerFocusWidth", 2);
            UIManager.put("Component.focusWidth", 2);
            UIManager.put("Component.focusColor", new Color(1, 68, 33));
            UIManager.put("TextField.margin", new Insets(10, 10, 10, 10));
            UIManager.put("ComboBox.padding", new Insets(10, 10, 10, 10));
            UIManager.put("Button.padding", new Insets(10, 20, 10, 20));

            // Global font and text color
            Font uiFont = new Font("SansSerif", Font.PLAIN, 16);
            Color textColor = new Color(1, 50, 32);

            for (Object key : UIManager.getLookAndFeelDefaults().keySet()) {
                if (key.toString().toLowerCase().contains("font")) {
                UIManager.put(key, uiFont);
            }
                if (key.toString().toLowerCase().contains("foreground")) {
                UIManager.put(key, textColor);
            }
        }
            FlatLightLaf.setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(GUI::new);
    }


}
