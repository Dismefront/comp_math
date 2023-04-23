package dismefront.gui;

import dismefront.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InputFields {

    protected JFrame frame;
    protected JPanel panel;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;
    JLabel label1;
    JLabel label2;
    JLabel label3;

    private String addWarning(String text, String warning) {
        if (warning == null)
            return text;
        return "<html>" + text + ": (<font color='red'>" + warning + "</font>)" + "</html>";
    }

    public InputFields(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        label1 = new JLabel("EPSILON:");
        panel.add(label1);
        panel.add(textField1);

        label2 = new JLabel("LEFT BOUNDARY:");
        panel.add(label2);
        panel.add(textField2);

        label3 = new JLabel("RIGHT BOUNDARY:");
        panel.add(label3);
        panel.add(textField3);
    }

    public void addSubmitButton(Controller controller) {
        JButton button = new JButton("Submit");
        button.addActionListener(controller);
        panel.add(button);
    }

    public Double getSelectedEPS() {
        return getDouble("EPSILON", textField1, label1);
    }

    public Double getSelectedLB() {
        return getDouble("LEFT BOUNDARY", textField2, label2);
    }

    public Double getSelectedRB() {
        return getDouble("RIGHT BOUNDARY", textField3, label3);
    }

    private Double getDouble(String name, JTextField textField, JLabel label) {
        String text = textField.getText().replace(",", ".");
        Double res;
        try {
            label.setText(addWarning(name + ":", null));
            res = Double.parseDouble(text);
        }
        catch (Exception ex) {
            label.setText(addWarning(name + ":", "unsupported field"));
            res = null;
        }
        return res;
    }

    public void add() {
        frame.add(panel);
    }

}
