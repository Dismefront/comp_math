package dismefront.main;

import dismefront.exception.ProgramClosedException;
import dismefront.functions.AllFunctions;
import dismefront.functions.AllSystems;
import dismefront.functions.Function;
import dismefront.graph.Graph;
import dismefront.io.ConsoleIn;
import dismefront.io.ConsoleOut;
import dismefront.methods.*;
import org.w3c.dom.ls.LSOutput;
import javax.swing.*;

public class Main {

    public static JFrame frame;
    
    public static void face() {
        frame = new JFrame("рофлолаба");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JLabel
        JLabel label = new JLabel("Лицо лабы!");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        // Add JLabel to JFrame
        frame.getContentPane().add(label);

        // Show JFrame
        frame.setVisible(true);
    }

    public static void main(String... args) {
        face();
        ConsoleOut consoleOut = new ConsoleOut();
        ConsoleIn consoleIn = new ConsoleIn();

        try {
            while (true) {
                consoleIn.waitFormatEnter(consoleOut);
                consoleOut.showGreetMessage();

                int var = consoleIn.waitVariantChoice(consoleOut);

                if (var == 1) {
                    consoleOut.showAllEquations();
                    int functionNum = consoleIn.waitFunctionEnter(consoleOut);
                    Methods.show();
                    int methodNum = consoleIn.waitMethodEnter(consoleOut);
                    System.out.println("EPSILON:");
                    double eps = consoleIn.waitDouble(consoleOut);
                    System.out.println("Interval");
                    double[] interval = consoleIn.waitInterval(consoleOut);

                    if (methodNum == 1) {
                        double x = ((Newton)Methods.getMethods().get(0))
                                .findRoot(AllFunctions.getFunctions()
                                        .get(functionNum - 1), interval[0], interval[1], eps);
                        System.out.println("x = " + x);
                    }
                    else if (methodNum == 2) {
                        double x = ((Secant)Methods.getMethods().get(1))
                                .findRoot(AllFunctions.getFunctions()
                                        .get(functionNum - 1), interval[0], interval[1], eps);
                        System.out.println("x = " + x);
                    }
                    else if (methodNum == 3) {
                        double x = ((SimpleIteration)Methods.getMethods().get(2))
                                .findRoot(AllFunctions.getFunctions()
                                        .get(functionNum - 1), interval[0], eps);
                        System.out.println("x = " + x);
                    }
                    Function[] f = new Function[] {AllFunctions.getFunctions().get(functionNum - 1)};
                    Graph.drawGraph(f,
                            -10, 10, -10, 10);
                }
                else if (var == 2) {
                    consoleOut.showAllSystems();
                    int systemNum = consoleIn.waitSystemEnter(consoleOut);
                    SystemMethods.show();
                    int methodNum = consoleIn.waitSystemMethodEnter(consoleOut);
                    System.out.println("EPSILON:");
                    double eps = consoleIn.waitDouble(consoleOut);
                    if (methodNum == 1) {
                        try {
                            double[] x0 = new double[AllSystems.getSystems().get(0).getFunctions().length];
                            double[] x = ((NewtonSystem) SystemMethods.getMethods().get(0))
                                    .solve(AllSystems.getSystems().get(systemNum - 1), x0, eps);
                            for (int i = 0; i < x.length; i++)
                                System.out.printf("X%d = %f; ", i, x[i]);
                            System.out.println();
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    Function[] f = AllSystems.getSystems().get(systemNum - 1).getFunctions();
                    Graph.drawGraph(f,
                            -10, 10, -10, 10);
                }
            }
        }
        catch (ProgramClosedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
