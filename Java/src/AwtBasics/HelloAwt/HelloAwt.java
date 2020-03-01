package AwtBasics.HelloAwt;

import javax.swing.*;

public class HelloAwt extends JFrame {
    public HelloAwt(){
        super("Hellow AWT!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}
