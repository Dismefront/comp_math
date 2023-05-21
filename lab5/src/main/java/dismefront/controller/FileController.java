package dismefront.controller;

import dismefront.gui.InputFields;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileController implements ActionListener {

    InputFields inputFields;

    public FileController(InputFields inputFields) {
        this.inputFields = inputFields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // User selected a file, read its contents
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    if (i == 0) {
                        inputFields.getXfield().setText(line);
                    }
                    else if (i == 1) {
                        inputFields.getYfield().setText(line);
                    }
                    i++;
                }
                reader.close();
            } catch (IOException ex) {
                System.out.println("No such file");
            }
        }



    }

}
