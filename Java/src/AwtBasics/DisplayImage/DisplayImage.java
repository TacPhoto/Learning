package AwtBasics.DisplayImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayImage extends JPanel {

    private BufferedImage image;

    public DisplayImage() throws IOException {
        super();


        File imageFile = new File("src/AwtBasics/DisplayImage/icon.png");
        try{
            image = ImageIO.read(imageFile);
        }catch (IOException e){
            System.out.println("Image reading error");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }
}
