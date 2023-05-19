package dismefront.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));
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
        integralResult.setText("Pearson correlation for linear function: " + integral);
        numberSubsequences.setText("Best approximation function: " + divnum);
        panel.add(integralResult);
        panel.add(numberSubsequences);
        panel.revalidate();
        panel.repaint();

        String filePath = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Pearson correlation for linear function: " + integral);
            writer.newLine();
            writer.write("Best approximation function: " + divnum);

            System.out.println("Data written to the file successfully.");
        } catch (IOException ex) {
            System.out.println("Error writing to the file: " + ex.getMessage());
        }
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
        if (warning == null) {
            warning = new JLabel(message);
            warning.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        }
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
