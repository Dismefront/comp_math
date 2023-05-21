package dismefront.gui;

import dismefront.controller.Controller;
import dismefront.controller.FileController;
import dismefront.logic.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class InputFields {

    protected JFrame frame;
    protected JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label2;
    public JLabel label3;
    public JTextField textField3;

    Polynomial2DEquation polynomial2DEquation = new Polynomial2DEquation(1, 2, -10);
    ExponentialEquation exponentialEquation = new ExponentialEquation(1, 2);
    LinearEquation linearEquation = new LinearEquation(4, -3);
    PowerEquation powerEquation = new PowerEquation(1.3, 2.24);
    Equation<Double, Double> trigEquation = new TrigEquation(-2, -1.12);

    public JTextField leftBorderField;
    public JTextField rightBorderField;
    private JLabel leftBorderLabel;
    private JLabel rightBorderLabel;
    private JSpinner jSpinner;

    private JLabel equationChooseLabel;
    private JRadioButton[] radio;

    private Equation<Double, Double> currentEquation;

    private String addWarning(String text, String warning) {
        if (warning == null)
            return text;
        return "<html>" + text + " (<font color='red'>" + warning + "</font>)" + "</html>";
    }

    private void addEquationChoose() {

        radio = new JRadioButton[5];
        equationChooseLabel = new JLabel("Choose an equation");
        radio[0] = new JRadioButton(polynomial2DEquation.what());
        radio[1] = new JRadioButton(exponentialEquation.what());
        radio[2] = new JRadioButton(linearEquation.what());
        radio[3] = new JRadioButton(powerEquation.what());
        radio[4] = new JRadioButton(trigEquation.what());
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(8, 8, 20, 1);
        jSpinner = new JSpinner(spinnerNumberModel);
        JLabel spinnerLabel =  new JLabel("Number of points [8; 20]");

        panel.add(spinnerLabel);
        panel.add(jSpinner);
        panel.add(equationChooseLabel);
        panel.add(radio[0]);
        panel.add(radio[1]);
        panel.add(radio[2]);
        panel.add(radio[3]);
        panel.add(radio[4]);

        for (int i = 0; i < radio.length; i++) {
            int finalI = i;
            radio[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < radio.length; j++) {
                        if (finalI != j) {
                            radio[j].setSelected(false);
                        }
                        else {
                            if (radio[j].isSelected()) {
                                currentEquation = getEquationByNumber(j);
                                Double lb = getDouble("Left boundary", leftBorderField, leftBorderLabel);
                                Double rb = getDouble("Right boundary", rightBorderField, rightBorderLabel);
                                if (lb == null || lb >= rb || Math.abs(lb) > 100) {
                                    lb = -3.0;
                                    leftBorderLabel.setText(addWarning("Left boundary", "unsupported field. reset to -3"));
                                }
                                if (rb == null || lb >= rb || Math.abs(rb) > 100) {
                                    rb = 3.0;
                                    rightBorderLabel.setText(addWarning("Right boundary", "unsupported field. reset to 3"));
                                }
                                int numberOfPoints = (int) jSpinner.getValue() - 1;
                                double step = (rb - lb) / numberOfPoints;
                                ArrayList<String> xVals = new ArrayList<>();
                                ArrayList<String> yVals = new ArrayList<>();
                                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                                for (double point = lb; point <= rb; point += step) {
                                    xVals.add(decimalFormat.format(point));
                                    yVals.add(decimalFormat.format(currentEquation.apply(point)));
                                }
                                textField1.setText(String.join(" ", xVals));
                                textField2.setText(String.join(" ", yVals));
                            }
                            else {
                                currentEquation = null;
                            }
                        }
                    }
                }

            });
        }

    }

    public Equation<Double, Double> getCurrentEquation() {
        return currentEquation;
    }

    private Equation<Double, Double> getEquationByNumber(int num) {
        if (num == 0) {
            return polynomial2DEquation;
        }
        else if (num == 1) {
            return exponentialEquation;
        }
        else if (num == 2) {
            return linearEquation;
        }
        else if (num == 3) {
            return powerEquation;
        }
        else if (num == 4) {
            return trigEquation;
        }
        return null;
    }

    public InputFields(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        textField1 = new JTextField();
        textField2 = new JTextField();
        leftBorderField = new JTextField();
        rightBorderField = new JTextField();

        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        leftBorderLabel = new JLabel("Left boundary");
        rightBorderLabel = new JLabel("Right boudnary");
        panel.add(leftBorderLabel);
        panel.add(leftBorderField);
        panel.add(rightBorderLabel);
        panel.add(rightBorderField);

        addEquationChoose();

        label1 = new JLabel("X points (space divided):");
        panel.add(label1);
        panel.add(textField1);

        label2 = new JLabel("Y points (space divided):");
        panel.add(label2);
        panel.add(textField2);

        label3 = new JLabel("Point to calculate value for:");
        panel.add(label3);
        textField3 = new JTextField();
        panel.add(textField3);
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
        return getArray("X points (space divided):", textField1, label1);
    }

    public ArrayList<Double> getYpoints() {
        return getArray("Y points (space divided):", textField2, label2);
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

    private Double getDouble(String name, JTextField textField, JLabel label) {
        String text = textField.getText().replace(",", ".");
        Double res;
        try {
            label.setText(addWarning(name, null));
            res = Double.parseDouble(text);
        }
        catch (Exception ex) {
            res = null;
        }
        return res;
    }

    public void add() {
        frame.add(panel);
    }

}
