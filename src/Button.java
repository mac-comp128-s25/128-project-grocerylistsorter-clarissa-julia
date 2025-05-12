import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class Button {
    private ListOrganizer organizer;
    private GUI gui;

    
    public Button(ListOrganizer organizer, GUI gui) {
        this.organizer = organizer;
        this.gui = gui;
    }

    /**
     * Method to be used by copy button in GUI. Creates pop up confirmation message when done
     */
    public void copyToClipboard(ActionEvent e) {
        String text = organizer.fullListString(); // correct source of table content
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        System.out.println("Copied to clipboard:\n" + text); // optional debug
        JOptionPane.showMessageDialog(gui, 
        "Content copied to clipboard!", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Checks if all options have been selected and adds it to the list
     * @param item 
     * @param price
     * @param quantity
     * @param category
     */
    public void addItem(String item, double price, int quantity, String category) {

        if (category.equals("Select Category")) {
            System.out.println("Please select a valid category.");
            return;
        }
    
        organizer.addToList(new Item(item, quantity, price, category));
        gui.refreshDisplay();
    }
    

    /**
     * Method to clear all items from the list and refresh the display
     */
    public void clearAllLists() {
        organizer.clearAll();
        gui.refreshDisplay();
    }
    

    
}
