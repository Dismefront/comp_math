package dismefront.gui;

import javax.swing.*;
import java.awt.*;

public class BaseFrame {

    private JFrame frame;
    private final String title = "Laboratory work 3";
    private final int screenWidth = 800;
    private final int screenHeight = 600;

    public BaseFrame() {
        frame = new JFrame(title);
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));
    }

    public void show() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

}
