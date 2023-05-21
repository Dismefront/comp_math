package dismefront.gui;

import dismefront.logic.Polynomial2DEquation;
import dismefront.logic.PolynomialEquation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public void outputAnswer(Double point, Double integral, Double divnum) {
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
        integralResult.setText("Calculated Lagrange result at %.2f: %.2f".formatted(point, integral));
        numberSubsequences.setText("Calculated Newton result at %.2f: %.2f".formatted(point, divnum));
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

    private Graph graph;

    public void addGraph(ArrayList<Double> xp, ArrayList<Double> yp, double lb, double rb) {
        if (graph == null)
            graph = new Graph();
        graph.updatePoints(xp, yp, lb, rb);
        graph.showGraph(panel);
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
