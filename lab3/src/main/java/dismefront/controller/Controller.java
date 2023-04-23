package dismefront.controller;

import dismefront.controller.Data;
import dismefront.gui.AnswerOutput;
import dismefront.gui.EquationChoose;
import dismefront.gui.InputFields;
import dismefront.gui.MethodChoose;
import dismefront.logic.Equation;
import dismefront.logic.Method;
import dismefront.methods.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Data data = null;

    EquationChoose equationChoose;
    InputFields inputFields;
    MethodChoose methodChoose;
    AnswerOutput answerOutput;

    public Controller(MethodChoose method, EquationChoose equation, InputFields input, AnswerOutput answer) {
        methodChoose = method;
        equationChoose = equation;
        inputFields = input;
        answerOutput = answer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Method method = methodChoose.getSelected();
        Equation equation = equationChoose.getSelected();
        Double eps = inputFields.getSelectedEPS();
        Double lb = inputFields.getSelectedLB();
        Double rb = inputFields.getSelectedRB();
        if (!(method != null && equation != null && eps != null && lb != null && rb != null))
            return;
        data = new Data(method, equation, eps, lb, rb);

        if (!validate())
            return;
        try {
            Result result = performMethod();
            answerOutput.updateWarning(null);
            answerOutput.outputAnswer(Double.toString(result.answer()),
                    Integer.toString(result.divisionNumber()));
        }
        catch (CouldNotConvergeException ex) {
            answerOutput.updateWarning("Could not calculate" +
                    " (over " + ConstantBoundary.MAX_SUBINTERVALS + " subsequences)");
            answerOutput.outputAnswer(null, null);
        }
    }

    public Result performMethod() throws CouldNotConvergeException {

        switch (data.method()) {
            case RECTANGLE_LEFT -> {
                return new RectangleLeft().solve(data.equation(), data.eps(), data.leftB(), data.rightB());
            }
            case RECTANGLE_MIDDLE -> {
                return new RectangleMiddle().solve(data.equation(), data.eps(), data.leftB(), data.rightB());
            }
            case RECTANGLE_RIGHT -> {
                return new RectangleRight().solve(data.equation(), data.eps(), data.leftB(), data.rightB());
            }
            case TRAPEZOIDAL -> {
                return new Trapezoidal().solve(data.equation(), data.eps(), data.leftB(), data.rightB());
            }
            case SIMPSON -> {
                return new Simpson().solve(data.equation(), data.eps(), data.leftB(), data.rightB());
            }
            default -> {
                return null;
            }
        }

    }

    public boolean validate() {
        if (data.eps() > 1000d) {
            answerOutput.updateWarning("EPSILON IS TOO LARGE (max=1000)");
            return false;
        }
        if (data.eps() <= 0) {
            answerOutput.updateWarning("EPSILON <= 0");
            return false;
        }
        if (data.leftB() > data.rightB()) {
            answerOutput.updateWarning("Incorrect boundaries(left > right)");
            return false;
        }
        if (Math.abs(data.leftB()) > 100000d) {
            answerOutput.updateWarning("Left boundary is too large(max=100000)");
            return false;
        }
        if (Math.abs(data.rightB()) > 100000d) {
            answerOutput.updateWarning("Right boundary is too large(max=100000)");
            return false;
        }
        return true;
    }

    public Data getData() {
        return data;
    }

}
