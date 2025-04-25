import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;


public class GUI extends JFrame {
    private PlaceholderTextField itemField;
    private PlaceholderTextField quantityField;
    private JComboBox<String> outputCombo;
    private JComboBox<String> categoryCombo;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    private JButton copyButton, undoButton, totalButton;
    private Button buttonHandler;
    private JCheckBox darkModeToggle;
    private JButton addItemButton;
    private ListOrganizer organizer;

    public GUI() {
        // Initialize components
        itemField = new PlaceholderTextField("Enter item");
        quantityField = new PlaceholderTextField("Enter quantity");
        outputCombo = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        categoryCombo = new JComboBox<>(new String[]{"Select Category","Produce", "Dairy & Eggs", "Bakery", 
        "Pantry Staples", "Meat & Seafood", "Frozen Food", "Snacks & Beverages", "Household Goods", 
        "Personal Care Items"}); //adds category drop-down menu
        displayArea = new JTextArea(5, 30);
        displayArea.setEditable(false);
        scrollPane = new JScrollPane(displayArea);
        buttonHandler = new Button();
        darkModeToggle = new JCheckBox("Dark Mode");
        addItemButton = new JButton("Add Item");
        organizer = new ListOrganizer();

        // Add customized font and padding
        Font inputFont = new Font("SansSerif", Font.PLAIN, 16);
        itemField.setFont(inputFont);
        quantityField.setFont(inputFont);
        outputCombo.setFont(inputFont);
        categoryCombo.setFont(inputFont);

        itemField.setPreferredSize(new Dimension(200, 40));
        quantityField.setPreferredSize(new Dimension(200, 40));
        outputCombo.setPreferredSize(new Dimension(200, 40));
        categoryCombo.setPreferredSize(new Dimension(200, 40));


        // Initialize buttons
        copyButton = new JButton("Copy");
        undoButton = new JButton("Undo");
        totalButton = new JButton("Total");

        // Set button actions
        copyButton.addActionListener(buttonHandler::copyToClipboard);
        undoButton.addActionListener(buttonHandler::undoLastAdd);
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
        addItemButton.addActionListener(e -> buttonHandler.addItem(
            quantityField.getText(),
            (String) outputCombo.getSelectedItem(),
            (String) categoryCombo.getSelectedItem()
        ));
        

        // Set layout for the JFrame
        setLayout(new BorderLayout());

        // Create input panel and add components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(itemField);
        inputPanel.add(quantityField);
        inputPanel.add(outputCombo);
        inputPanel.add(categoryCombo);
        inputPanel.add(addItemButton);

        // Create button panel and add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(copyButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(darkModeToggle);

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
    private void refreshDisplay(){
        displayArea.setText(organizer.fullListString());
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
