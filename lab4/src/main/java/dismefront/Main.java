package dismefront;

import dismefront.controller.Controller;
import dismefront.controller.FileController;
import dismefront.gui.*;

public class Main {

    // 1.1 2.3 3.7 4.5 5.4 6.8 7.5 8
    // 2.73 5.12 7.74 8.91 10.59 12.75 13.43 14

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
