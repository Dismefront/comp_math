package dismefront.gui;

import dismefront.logic.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class EquationChoose {

    protected JFrame frame;
    protected JPanel panel;
    protected JLabel warning;
    protected ButtonGroup buttonGroup;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;
    JRadioButton radioButton4;
    JRadioButton radioButton5;

    ArrayList<Equation> arrayList = new ArrayList<>();

    public void addEquations() {
        arrayList.add(new Equation1());
        arrayList.add(new Equation2());
        arrayList.add(new Equation3());
        arrayList.add(new Equation4());
        arrayList.add(new Equation5());
    }

    public EquationChoose(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addEquations();

        radioButton1 = new JRadioButton(arrayList.get(0).what());
        radioButton2 = new JRadioButton(arrayList.get(1).what());
        radioButton3 = new JRadioButton(arrayList.get(2).what());
        radioButton4 = new JRadioButton(arrayList.get(3).what());
        radioButton5 = new JRadioButton(arrayList.get(4).what());

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

    public Equation getSelected() {
        if (warning == null)
            warning = new JLabel();
        int num = 0;
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                panel.remove(warning);
                frame.revalidate();
                frame.repaint();
                return arrayList.get(num);
            }
            num++;
        }
        warning.setText("No equation is chosen");
        warning.setForeground(Color.RED);
        panel.add(warning, 0);
        frame.revalidate();
        frame.repaint();
        return null;
    }

}
