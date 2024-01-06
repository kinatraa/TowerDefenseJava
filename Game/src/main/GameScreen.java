package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {
    private BufferedImage img;
    private Random random;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    public GameScreen(BufferedImage img){
        this.img = img;

        loadSprites();
        random = new Random();
    }

    private void loadSprites(){
        for(int x = 0; x < 23; x++){
            for(int y = 0; y < 13; y++){
                sprites.add(img.getSubimage(x*64, y*64, 64, 64));
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int x = 0; x < 23; x++){
            for(int y = 0; y < 13; y++){
                g.drawImage(sprites.get(getRandomInt()), x*64, y*64, null);
            }
        }
//        g.drawImage(sprites.get(3), 0, 0, null);
//        g.drawImage(img.getSubimage(0, 0, 64, 64), 0, 0, null);
    }

    private int getRandomInt(){
        return random.nextInt(298);
    }
}
