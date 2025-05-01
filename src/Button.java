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

    public void addItem(String item, double price, int quantity, String category) {

        if (category.equals("Select Category")) {
            System.out.println("Please select a valid category.");
            return;
        }
    
        organizer.addToList(new Item(item, quantity, price, category));
        gui.refreshDisplay();
    }
    

    public void clearAllLists() {
        organizer.clearAll();
        gui.refreshDisplay();
    }
    

    
}
