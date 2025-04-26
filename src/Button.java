import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Button {
    private List<String> items = new ArrayList<>();
    private List<String> prices = new ArrayList<>();
    private List<String> history = new ArrayList<>();
    private ListOrganizer organizer;
    private GUI gui;

    public Button(ListOrganizer organizer, GUI gui) {
        this.organizer = organizer;
        this.gui = gui;
    }

    public void copyToClipboard(ActionEvent e) {
        String text = String.join("\n", items);
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    public void undoLastAdd(ActionEvent e) {
        if (!history.isEmpty()) {
            String lastAction = history.remove(history.size() - 1);
            // Logic to undo the last action
        }
    }

    public void calculateTotal(ActionEvent e) {
        // Logic to calculate total
    }

    public void addItem(String item, String price, String quantity, String category) {
        if (category.equals("Select Category")) {
            System.out.println("Please select a valid category.");
            return;
        }
    
        double priceValue; //TODO: temporary placeholder until we get call correct
        try {
            priceValue = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price entered.");
            return;
        }
    
        organizer.addToList(new Item(item, quantity, priceValue, category));
        gui.refreshDisplay();
    }
    

    public void clearAllLists() {
        organizer.clearAll();
        gui.refreshDisplay();
    }
    

    
}
