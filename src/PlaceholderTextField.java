import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlaceholderTextField extends JTextField {
    /**
     * Creates the search box for user input
     * @param placeholder
     */
    public PlaceholderTextField(String placeholder) {
        setText(placeholder);
        setForeground(Color.GRAY);
        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                }
            }
        });
    }
}
