import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame{
    public MainFrame() {

        int argument = 0;
        String operation = "";

        JTextField operationField = new JTextField();
        JTextField bufferField = new JTextField();

        setTitle("Calculator");
        setBounds(100, 100, 300, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        add(top, BorderLayout.CENTER);
        top.setLayout(new GridLayout(4, 3));
        JPanel btm = new JPanel();
        add(btm, BorderLayout.SOUTH);
        btm.setLayout(new BorderLayout());

        JTextField inputField = new JTextField();

        DigitButtonListener digitButtonListener = new DigitButtonListener(inputField);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            top.add(btn);
            btn.addActionListener(digitButtonListener);
        }

        JButton submit = new JButton(("="));
        submit.addActionListener(new SubmitButtonListener(inputField, operationField, bufferField));
        top.add(submit);

        JButton add = new JButton(("+"));
        add.addActionListener(new OperationButtonListener(inputField, operationField, bufferField));
        top.add(add);

        JButton subtract = new JButton(("-"));
        subtract.addActionListener(new OperationButtonListener(inputField, operationField, bufferField));
        top.add(subtract);

        JButton multiply = new JButton(("*"));
        multiply.addActionListener(new OperationButtonListener(inputField, operationField, bufferField));
        top.add(multiply);

        JButton division = new JButton(("/"));
        division.addActionListener(new OperationButtonListener(inputField, operationField, bufferField));
        top.add(division);

        JButton pow = new JButton(("^"));
        top.add(pow);

        btm.add(inputField, BorderLayout.CENTER);

        JButton clear = new JButton(("C"));
        clear.addActionListener(new ClearButtonListener(inputField, operationField, bufferField));
        btm.add(clear, BorderLayout.EAST);

        setVisible(true);
    }

    private static class SubmitButtonListener implements ActionListener{
        private JTextField inputField;
        private JTextField operationField;
        private JTextField bufferField;


        public SubmitButtonListener(JTextField inputField, JTextField operationField, JTextField bufferField) {
            this.inputField = inputField;
            this.operationField = operationField;
            this.bufferField = bufferField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!operationField.getText().equals("")) {

                int arg1 = Integer.parseInt(bufferField.getText());
                int arg2 = Integer.parseInt(inputField.getText());
                String res = "";

                switch (operationField.getText()) {
                    case "+":
                        res = String.valueOf(arg1 + arg2);
                        break;
                    case "-":
                        res = String.valueOf(arg1 - arg2);
                        break;
                    case "*":
                        res = String.valueOf(arg1 * arg2);
                        break;
                    case "/":
                        if (arg2 != 0) {
                            res = String.valueOf(arg1 / arg2);
                            break;
                        }
                        else {
                            res = "ZeroDivisionError";
                        }
                }
                bufferField.setText(inputField.getText());
                inputField.setText(res);
            }
        }
    }

    private static class OperationButtonListener implements ActionListener{
        private JTextField inputField;
        private JTextField operationField;
        private JTextField bufferField;

        public OperationButtonListener(JTextField inputField, JTextField operationField, JTextField bufferField) {
            this.inputField = inputField;
            this.operationField = operationField;
            this.bufferField = bufferField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!inputField.getText().equals("")) {
                JButton btn = (JButton) e.getSource();
                bufferField.setText(inputField.getText());
                operationField.setText(btn.getText());
                inputField.setText("");
            }

        }
    }

    private static class ClearButtonListener implements ActionListener{
        private JTextField inputField;
        private JTextField operationField;
        private JTextField bufferField;

        public ClearButtonListener(JTextField inputField, JTextField operationField, JTextField bufferField) {
            this.inputField = inputField;
            this.operationField = operationField;
            this.bufferField = bufferField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inputField.setText("");
            operationField.setText("");
            bufferField.setText("");

        }
    }
}
