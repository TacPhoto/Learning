package AwtBasics.Shapes;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

public class Shapes extends JFrame{

    public Shapes() {
        super( "Flow Layout" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new MyPanel();
        add(panel);
        pack();

        setVisible(true);
    }

}
