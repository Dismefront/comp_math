package dismefront.controller;

import dismefront.gui.AnswerOutput;
import dismefront.gui.InputFields;
import dismefront.methods.Lagrange;
import dismefront.methods.Newton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    InputFields inputFields;
    AnswerOutput answerOutput;
    ArrayList<Double> xp, yp;

    public Controller(InputFields input, AnswerOutput answer) {
        inputFields = input;
        answerOutput = answer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xp = inputFields.getXpoints();
        yp = inputFields.getYpoints();
        if (!validate())
            return;
        answerOutput.updateWarning(null);
        double rb = 3d;
        try {
            rb = Double.parseDouble(inputFields.rightBorderField.getText());
        }
        catch (Exception ignored) {}
        double lb = -3d;
        try {
            lb = Double.parseDouble(inputFields.leftBorderField.getText());
        }
        catch (Exception ignored) {}
        if (rb <= lb) {
            lb = -3d;
            rb = 3d;
        }
        Double point = Double.NaN;
        try {
            point = Double.parseDouble(inputFields.textField3.getText());
        }
        catch (Exception ignored) {}
        answerOutput.addGraph(xp, yp, lb, rb);
        Lagrange lagrangeInt = new Lagrange(xp, yp);
        Newton newtonInt = new Newton(xp, yp);
        newtonInt.printDifference();
        answerOutput.outputAnswer(point,
                lagrangeInt.interpolate(point),
                newtonInt.interpolate(point));
    }

    public boolean validate() {
        boolean ok = true;
        if (xp == null || yp == null)
            return false;
        if (xp.size() != yp.size()) {
            ok = false;
            answerOutput.updateWarning("Array sizes are not equal (%d and %d)".formatted(xp.size(), yp.size()));
        }
        else if (xp.size() < 8) {
            ok = false;
            answerOutput.updateWarning("Must be at least 8 X points");
        }
        else if (yp.size() < 8) {
            ok = false;
            answerOutput.updateWarning("Must be at least 8 Y points");
        }
        return ok;
    }

}
