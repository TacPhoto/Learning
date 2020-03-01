package AwtBasics.Shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyPanel extends JPanel {

    public MyPanel(){
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Rectangle2D rectangle = new Rectangle2D.Float(10,10, 60, 60);
        Ellipse2D circle = new Ellipse2D.Float(10, 10, 60, 60);

        g2d.draw(rectangle);
        g2d.draw(circle);
    }
}
