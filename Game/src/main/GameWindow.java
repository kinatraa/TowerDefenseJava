package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameWindow extends JFrame {
    private GameScreen gameScreen;
    private BufferedImage img;
    public GameWindow(){

        importImg();

        setSize(1280,768);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen(img);
        add(gameScreen);
    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/towerDefense_tilesheet.png");
        try {
            assert is != null;
            img = ImageIO.read(is);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
