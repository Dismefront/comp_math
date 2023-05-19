package dismefront.controller;

import dismefront.gui.AnswerOutput;
import dismefront.gui.Graph;
import dismefront.gui.InputFields;
import dismefront.logic.*;
import dismefront.methods.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements ActionListener {

    InputFields inputFields;
    AnswerOutput answerOutput;
    ArrayList<Double> xp, yp;

    public Controller(InputFields input, AnswerOutput answer) {
        inputFields = input;
        answerOutput = answer;
    }

    private double[] linCoeff;
    private double[] poly2DCoeff;
    private double[] poly3DCoeff;
    private double[] exponentialCoeff;
    private double[] logCoeff;
    private double[] pwCoeff;

    @Override
    public void actionPerformed(ActionEvent e) {
        xp = inputFields.getXpoints();
        yp = inputFields.getYpoints();
        if (!validate())
            return;
        answerOutput.updateWarning(null);
        linCoeff = new Linear().solve(xp, yp);
        poly2DCoeff = new Polynomial2D().solve(xp, yp);
        poly3DCoeff = new Polynomial3D().solve(xp, yp);
        exponentialCoeff = new Exponential().solve(xp, yp);
        logCoeff = new Logarithmic().solve(xp, yp);
        pwCoeff = new PowerApproximation().solve(xp, yp);
        new Graph(
                new LinearEquation(linCoeff[1], linCoeff[0]),
                new Polynomial2DEquation(poly2DCoeff[0], poly2DCoeff[1], poly2DCoeff[2]),
                new Polynomial3DEquation(poly3DCoeff[0], poly3DCoeff[1], poly3DCoeff[2], poly3DCoeff[3]),
                new ExponentialEquation(exponentialCoeff[0], exponentialCoeff[1]),
                new LogarithmicEquation(logCoeff[0], logCoeff[1]),
                new PowerEquation(pwCoeff[0], pwCoeff[1])
                ).main();
        String pierson = calculatePierson();
        String bestMethod = calculateBest();
        answerOutput.outputAnswer(pierson, bestMethod);
    }

    public String calculateBest() {
        String ans = "";
        double eps = Double.MAX_VALUE;
        double sum = 0.0;
        LinearEquation linearEquation = new LinearEquation(linCoeff[1], linCoeff[0]);
        Polynomial2DEquation polynomial2DEquation = new Polynomial2DEquation(poly2DCoeff[0], poly2DCoeff[1], poly2DCoeff[2]);
        Polynomial3DEquation polynomial3DEquation = new Polynomial3DEquation(poly3DCoeff[0], poly3DCoeff[1], poly3DCoeff[2], poly3DCoeff[3]);
        ExponentialEquation exponentialEquation = new ExponentialEquation(exponentialCoeff[0], exponentialCoeff[1]);
        LogarithmicEquation logarithmicEquation = new LogarithmicEquation(logCoeff[0], logCoeff[1]);
        PowerEquation powerEquation = new PowerEquation(pwCoeff[0], pwCoeff[1]);

        double sigm1 = 0.0;
        double sigm2 = 0.0;

        for (int i = 0; i < yp.size(); i++) {
            sigm1 += Math.pow(linearEquation.apply(xp.get(i)) - yp.get(i), 2);
        }
        sigm1 /= yp.size();
        System.out.println(Math.pow(sigm1, 0.5));

        for (int i = 0; i < yp.size(); i++) {
            sigm2 += Math.pow(polynomial2DEquation.apply(xp.get(i)) - yp.get(i), 2);
        }
        sigm2 /= yp.size();
        System.out.println(Math.pow(sigm2, 0.5));

        for (int i = 0; i < yp.size(); i++) {
            sum += Math.pow(linearEquation.apply(linearEquation.apply(xp.get(i))) - yp.get(i), 2);
        }
        if (sum < eps) {
            ans = "Linear";
            eps = sum;
        }
        sum = 0;
        for (int i = 0; i < yp.size(); i++) {
            sum += Math.pow(polynomial2DEquation.apply(polynomial2DEquation.apply(xp.get(i))) - yp.get(i), 2);
        }
        if (sum < eps) {
            ans = "Polynomial 2D";
            eps = sum;
        }
        sum = 0;
        for (int i = 0; i < yp.size(); i++) {
            sum += Math.pow(polynomial3DEquation.apply(polynomial3DEquation.apply(xp.get(i))) - yp.get(i), 2);
        }
        if (sum < eps) {
            ans = "Polynomial 3D";
            eps = sum;
        }
        sum = 0;
        for (int i = 0; i < yp.size(); i++) {
            sum += Math.pow(exponentialEquation.apply(exponentialEquation.apply(xp.get(i))) - yp.get(i), 2);
        }
        if (sum < eps) {
            ans = "Exponential";
            eps = sum;
        }
        sum = 0;
        for (int i = 0; i < yp.size(); i++) {
            sum += Math.pow(powerEquation.apply(powerEquation.apply(xp.get(i))) - yp.get(i), 2);
        }
        if (sum < eps) {
            ans = "Power";
            eps = sum;
        }
        sum = 0;
        for (int i = 0; i < yp.size(); i++) {
            sum += Math.pow(logarithmicEquation.apply(logarithmicEquation.apply(xp.get(i))) - yp.get(i), 2);
        }
        if (sum < eps) {
            ans = "Logarithmic";
        }
        return ans;
    }

    public String calculatePierson() {
        double meanX = 0.0;
        for (int i = 0; i < xp.size(); i++)
            meanX += xp.get(i);
        meanX /= xp.size();
        double meanY = 0.0;
        for (int i = 0; i < yp.size(); i++)
            meanY += yp.get(i);
        meanY /= yp.size();

        double div1 = 0.0;
        double div2 = 0.0;
        double div3 = 0.0;
        for (int i = 0; i < xp.size(); i++) {
            div1 += (xp.get(i) - meanX) * (yp.get(i) - meanY);
            div2 += (xp.get(i) - meanX) * (xp.get(i) - meanX);
            div3 += (yp.get(i) - meanX) * (yp.get(i) - meanX);
        }
        return Double.toString(div1 / Math.sqrt(div2 * div3));
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
