package dismefront;

import dismefront.controller.Controller;
import dismefront.gui.*;

public class Main {

    public static void main(String ... args) {

        BaseFrame frame = new BaseFrame();
        MethodChoose methodChoose = new MethodChoose(frame.getFrame());
        methodChoose.add();
        EquationChoose equationChoose = new EquationChoose(frame.getFrame());
        equationChoose.add();
        InputFields inputFields = new InputFields(frame.getFrame());
        AnswerOutput answerOutput = new AnswerOutput(frame.getFrame());

        Controller controller = new Controller(methodChoose, equationChoose, inputFields, answerOutput);

        inputFields.addSubmitButton(controller);
        inputFields.add();
        answerOutput.add();

        frame.show();
    }

}
