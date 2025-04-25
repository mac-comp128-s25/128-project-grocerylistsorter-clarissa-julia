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

    public void addItem(String item, String quantity, String category) {
        // ToDo: add implementation
        // listOrganizer.addToList(selected item dropdown, inputted quantity (String), individual price from map, selected category dropdown)

        if (category.equals("Select Category")) {
            System.out.println("Please select a valid category.");
            return;
        }

        System.out.println("Adding item: " + item + ", Quantity: " + quantity + ", Category: " + category);

    }
}
