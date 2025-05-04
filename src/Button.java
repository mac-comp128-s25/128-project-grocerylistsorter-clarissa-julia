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
