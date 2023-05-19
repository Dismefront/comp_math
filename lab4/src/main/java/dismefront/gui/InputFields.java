package dismefront.gui;

import dismefront.controller.Controller;
import dismefront.controller.FileController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class InputFields {

    protected JFrame frame;
    protected JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;

    private String addWarning(String text, String warning) {
        if (warning == null)
            return text;
        return "<html>" + text + " (<font color='red'>" + warning + "</font>)" + "</html>";
    }

    public InputFields(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        textField1 = new JTextField();
        textField2 = new JTextField();
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        label1 = new JLabel("Точки x через пробел:");
        panel.add(label1);
        panel.add(textField1);

        label2 = new JLabel("Точки y через пробел:");
        panel.add(label2);
        panel.add(textField2);
    }

    public void addSubmitButton(Controller controller) {
        JButton button = new JButton("Submit");
        button.addActionListener(controller);
        panel.add(button);
    }

    public JTextField getXfield() {
        return textField1;
    }

    public JTextField getYfield() {
        return textField2;
    }

    public void addOpenFileButton(FileController fileController) {
        JButton button = new JButton("File");
        button.addActionListener(fileController);
        panel.add(button);
    }

    public ArrayList<Double> getXpoints() {
        return getArray("Точки x через пробел:", textField1, label1);
    }

    public ArrayList<Double> getYpoints() {
        return getArray("Точки y через пробел:", textField2, label2);
    }

    private ArrayList<Double> getArray(String name, JTextField textField, JLabel label) {
        String text = textField.getText().replace(",", ".");
        ArrayList<Double> res = new ArrayList<>();
        try {
            label.setText(addWarning(name, null));
            String[] strDoubles = text.split(" ");
            for (String strDouble : strDoubles) {
                res.add(Double.parseDouble(strDouble));
            }
        }
        catch (Exception ex) {
            label.setText(addWarning(name, "unsupported field"));
            res = null;
        }
        return res;
    }

    public void add() {
        frame.add(panel);
    }

}
