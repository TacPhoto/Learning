package AwtBasics.LayoutManagers;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        super( "Flow Layout" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocation(50,50);
        setLayout(new FlowLayout());

        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3"));

        setVisible(true);
    }

}