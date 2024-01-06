package Scenes;

import main.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    public Menu(GameWindow game) {
        super(game);
        importImg();
        loadSprites();
        random = new Random();
    }

    @Override
    public void render(Graphics g) {
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 12; y++){
                g.drawImage(sprites.get(getRandomInt()), x*64, y*64, null);
            }
        }
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

    private void loadSprites(){
        for(int x = 0; x < 23; x++){
            for(int y = 0; y < 13; y++){
                sprites.add(img.getSubimage(x*64, y*64, 64, 64));
            }
        }
    }

    private int getRandomInt(){
        return random.nextInt(298);
    }
}
