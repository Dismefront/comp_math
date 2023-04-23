package dismefront.gui;

import dismefront.logic.Method;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Enumeration;

public class MethodChoose {

    protected JFrame frame;
    protected JPanel panel;
    protected JLabel warning;
    protected ButtonGroup buttonGroup;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;
    JRadioButton radioButton4;
    JRadioButton radioButton5;

    public MethodChoose(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        radioButton1 = new JRadioButton("Rectangle Method (left)");
        radioButton2 = new JRadioButton("Rectangle Method (middle)");
        radioButton3 = new JRadioButton("Rectangle Method (right)");
        radioButton4 = new JRadioButton("Trapezoidal Method");
        radioButton5 = new JRadioButton("Simpson's Method");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        buttonGroup.add(radioButton5);

        panel.setBorder(new EmptyBorder(50, 50, 50, 0));
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);
        panel.add(radioButton4);
        panel.add(radioButton5);

    }

    public void add() {
        frame.add(panel);
    }

    public Method getSelected() {
        if (warning == null)
            warning = new JLabel();
        int num = 0;
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                panel.remove(warning);
                frame.revalidate();
                frame.repaint();
                return Method.values()[num];
            }
            num++;
        }
        warning.setText("No method is chosen");
        warning.setForeground(Color.RED);
        panel.add(warning, 0);
        frame.revalidate();
        frame.repaint();
        return null;
    }

}
