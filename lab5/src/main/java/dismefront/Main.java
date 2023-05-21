package dismefront;

import dismefront.controller.Controller;
import dismefront.controller.FileController;
import dismefront.gui.*;

public class Main {

    public static void main(String ... args) {

        BaseFrame frame = new BaseFrame();
        InputFields inputFields = new InputFields(frame.getFrame());
        AnswerOutput answerOutput = new AnswerOutput(frame.getFrame());

        Controller controller = new Controller(inputFields, answerOutput);
        FileController fc = new FileController(inputFields);

        inputFields.addSubmitButton(controller);
        inputFields.addOpenFileButton(fc);
        inputFields.add();
        answerOutput.add();

        frame.show();
    }

}
