import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private PlaceholderTextField itemField;
    private PlaceholderTextField quantityField;
    private JComboBox<String> outputCombo;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    private JButton copyButton, undoButton, totalButton;
    private Button buttonHandler;

    public GUI() {
        // Initialize components
        itemField = new PlaceholderTextField("Enter item");
        quantityField = new PlaceholderTextField("Enter quantity");
        outputCombo = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        scrollPane = new JScrollPane(displayArea);
        buttonHandler = new Button();

        // Initialize buttons
        copyButton = new JButton("Copy");
        undoButton = new JButton("Undo");
        totalButton = new JButton("Total");

        // Set button actions
        copyButton.addActionListener(buttonHandler::copyToClipboard);
        undoButton.addActionListener(buttonHandler::undoLastAdd);
        totalButton.addActionListener(buttonHandler::calculateTotal);

        // Set layout for the JFrame
        setLayout(new BorderLayout());

        // Create input panel and add components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(itemField);
        inputPanel.add(quantityField);
        inputPanel.add(outputCombo);

        // Create button panel and add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(copyButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(totalButton);

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

    public static void main(String[] args) {
        // Ensure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(GUI::new);
    }
}
