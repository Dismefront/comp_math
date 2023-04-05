package dismefront.io;

import dismefront.functions.AllFunctions;
import dismefront.functions.AllSystems;
import dismefront.functions.Function;
import dismefront.functions.Function1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleOut {

    public void showGreetMessage() {
        System.out.println("Choose any of the options:");
    }

    public void showAllEquations() {
        AllFunctions.getFunctions().forEach(x -> System.out.printf("%d) %s\n", x.id(), x.what()));
    }

    public void showVariants() {
        System.out.println("1) Equations");
        System.out.println("2) System of equations");
    }

    public void showAllSystems() {
        AllSystems.getSystems().forEach(x -> System.out.printf("%d)\n%s\n", x.id(), x.what()));
    }

    public void showInputFormats() {
        System.out.println("1) Read from console");
        System.out.println("2) Read from file");
    }

    public void inviteFilenameEnter() {
        System.out.println("Enter the filename: ");
    }

    public void fileNotFound() {
        System.err.println("The file has not been found");
    }

    public void printError(InputError err) {
        switch (err) {
            case WRONG_FORMAT -> {
                System.err.println("Only Integers are allowed");
            }
            case OUT_OF_BOUNDS -> {
                System.err.println("There is no such element with given number");
            }
        }
    }

}
