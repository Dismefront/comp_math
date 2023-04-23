package dismefront.io;

import dismefront.exception.ProgramClosedException;
import dismefront.functions.AllFunctions;
import dismefront.functions.AllSystems;
import dismefront.methods.Methods;
import dismefront.methods.SystemMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleIn {

    private Scanner userInputScanner;
    private Scanner fileInputScanner;

    public ConsoleIn() {
        userInputScanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return fileInputScanner == null ? userInputScanner : fileInputScanner;
    }

    public int getIntChoise() throws ProgramClosedException {
        Scanner scanner = getScanner();
        int choice;
        try {
            choice = Integer.parseInt(scanner.next());
        } catch (NumberFormatException ex) {
            choice = -1;
        } catch (NoSuchElementException ex) {
            throw new ProgramClosedException();
        }
        return choice;
    }

    public double getDoubleChoise() throws ProgramClosedException {
        Scanner scanner = getScanner();
        double choice;
        try {
            choice = Double.parseDouble(scanner.next().replace(",", "."));
        } catch (NumberFormatException ex) {
            throw new RuntimeException();
        } catch (NoSuchElementException ex) {
            throw new ProgramClosedException();
        }
        return choice;
    }

    public String getStringChoice() throws ProgramClosedException {
        Scanner scanner = getScanner();
        String str;
        try {
            str = scanner.next();
        } catch (NumberFormatException ex) {
            str = null;
        } catch (NoSuchElementException ex) {
            throw new ProgramClosedException();
        }
        return str;
    }

    public String waitFilenameEnter(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            return getStringChoice();
        }
    }

    public int waitMethodEnter(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            int choice = getIntChoise();
            if (choice >= 1 && choice <= Methods.getMethods().size())
                return choice;
            if (choice == -1)
                console.printError(InputError.WRONG_FORMAT);
            else
                console.printError(InputError.OUT_OF_BOUNDS);
        }
    }

    public double waitDouble(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            try {
                double choice = getDoubleChoise();
                return choice;
            }
            catch (RuntimeException ex) {
                console.printError(InputError.WRONG_FORMAT);
            }
        }
    }

    public double[] waitInterval(ConsoleOut console) throws ProgramClosedException {
        double[] ret = new double[2];
        ret[0] = waitDouble(console);
        ret[1] = waitDouble(console);
        return ret;
    }

    public int waitSystemMethodEnter(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            int choice = getIntChoise();
            if (choice >= 1 && choice <= SystemMethods.getMethods().size())
                return choice;
            if (choice == -1)
                console.printError(InputError.WRONG_FORMAT);
            else
                console.printError(InputError.OUT_OF_BOUNDS);
        }
    }

    public int waitSystemEnter(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            int choice = getIntChoise();
            if (choice >= 1 && choice <= AllSystems.size())
                return choice;
            if (choice == -1)
                console.printError(InputError.WRONG_FORMAT);
            else
                console.printError(InputError.OUT_OF_BOUNDS);
        }
    }

    public int waitVariantChoice(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            console.showVariants();
            int choice = getIntChoise();
            if (choice >= 1 && choice <= 2)
                return choice;
            if (choice == -1)
                console.printError(InputError.WRONG_FORMAT);
            else
                console.printError(InputError.OUT_OF_BOUNDS);
        }
    }

    public int waitFormatEnter(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            console.showInputFormats();
            int choice = getIntChoise();
            if (choice == 1) {
                fileInputScanner = null;
            }
            else if (choice == 2) {
                console.inviteFilenameEnter();
                String filename = waitFilenameEnter(console);
                try {
                    FileInputStream stream = new FileInputStream(filename);
                    fileInputScanner = new Scanner(stream);
                }
                catch (FileNotFoundException ex) {
                    console.fileNotFound();
                    fileInputScanner = null;
                }
            }
            if (choice < 1 || choice > 2)
                console.printError(InputError.OUT_OF_BOUNDS);
            return choice;
        }
    }

    public int waitFunctionEnter(ConsoleOut console) throws ProgramClosedException {
        while (true) {
            int choice = getIntChoise();
            if (choice >= 1 && choice <= AllFunctions.size())
                return choice;
            if (choice == -1)
                console.printError(InputError.WRONG_FORMAT);
            else
                console.printError(InputError.OUT_OF_BOUNDS);
        }
    }

}
