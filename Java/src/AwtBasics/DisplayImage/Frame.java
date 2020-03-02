package AwtBasics.DisplayImage;

import javax.swing.*;
import java.io.IOException;

public class Frame extends JFrame {
    public Frame() throws IOException {
        super("Pic");

        JPanel imgPanel = new DisplayImage();
        add(imgPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
