package dismefront.gui;

import dismefront.logic.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class AnswerOutput {

    protected JFrame frame;
    protected JPanel panel;
    private JLabel warning;
    private JLabel integralResult;
    private JLabel numberSubsequences;

    public AnswerOutput(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(200, 200));
    }

    public void outputAnswer(String integral, String divnum) {
        if (integral == null) {
            if (integralResult != null) {
                panel.remove(integralResult);
                panel.remove(numberSubsequences);
            }
            integralResult = null;
            numberSubsequences = null;
            panel.revalidate();
            panel.repaint();
            return;
        }
        if (integralResult == null)
            integralResult = new JLabel();
        if (numberSubsequences == null)
            numberSubsequences = new JLabel();
        integralResult.setText("The calculated integral: " + integral);
        numberSubsequences.setText("The number of subsequences: " + divnum);
        panel.add(integralResult);
        panel.add(numberSubsequences);
        panel.revalidate();
        panel.repaint();
    }

    public void updateWarning(String message) {
        if (message == null) {
            if (warning != null)
                panel.remove(warning);
            warning = null;
            panel.revalidate();
            panel.repaint();
            return;
        }
        if (warning == null)
            warning = new JLabel(message);
        else
            warning.setText(message);
        warning.setForeground(Color.RED);
        panel.add(warning);
        panel.revalidate();
        panel.repaint();
    }

    public void add() {
        frame.add(panel);
    }

}
