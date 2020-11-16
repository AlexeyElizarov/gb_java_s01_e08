import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitButtonListener implements ActionListener {
    private JTextField inputField;

    public DigitButtonListener(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder(inputField.getText());
        JButton btn = (JButton) e.getSource();
        sb.append(btn.getText());
        inputField.setText(sb.toString());
    }
}
