package AwtBasics.DisplayImage;

import java.awt.*;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Frame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}