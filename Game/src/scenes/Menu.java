package scenes;

import ui.MyButton;
import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    private MyButton bPlaying, bSettings, bQuit, bEdit;
    public Menu(GameWindow game) {
        super(game);
        random = new Random();
        initButtons();
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 100;
        int yOffset = 125;
        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Edit", x, y+yOffset, w, h);
        bSettings = new MyButton("Setting", x, y+2*yOffset, w, h);
        bQuit = new MyButton("Quit", x, y+3*yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bPlaying.getBounds().contains(x, y)){
            bPlaying.resetBooleans();
            SetGameState(PLAYING);
        }
        else if(bEdit.getBounds().contains(x, y)){
            bEdit.resetBooleans();
            SetGameState(EDIT);
        }
        else if(bSettings.getBounds().contains(x, y)){
            bSettings.resetBooleans();
            SetGameState(SETTINGS);
        }
        else if(bQuit.getBounds().contains(x, y)){
            bQuit.resetBooleans();
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);
        if(bPlaying.getBounds().contains(x, y)){
            bPlaying.setMouseOver(true);
        }
        else if(bEdit.getBounds().contains(x, y)){
            bEdit.setMouseOver(true);
        }
        else if(bSettings.getBounds().contains(x, y)){
            bSettings.setMouseOver(true);
        }
        else if(bQuit.getBounds().contains(x, y)){
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bPlaying.setMousePressed(false);
        bEdit.setMousePressed(false);
        bSettings.setMousePressed(false);
        bQuit.setMousePressed(false);
        if(bPlaying.getBounds().contains(x, y)){
            bPlaying.setMousePressed(true);
        }
        else if(bEdit.getBounds().contains(x, y)){
            bEdit.setMousePressed(true);
        }
        else if(bSettings.getBounds().contains(x, y)){
            bSettings.setMousePressed(true);
        }
        else if(bQuit.getBounds().contains(x, y)){
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

//    private void importImg(){
//        InputStream is = getClass().getResourceAsStream("/towerDefense_tilesheet.png");
//        try {
//            assert is != null;
//            img = ImageIO.read(is);
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void loadSprites(){
//        for(int x = 0; x < 23; x++){
//            for(int y = 0; y < 13; y++){
//                sprites.add(img.getSubimage(x*32, y*32, 32, 32));
//            }
//        }
//    }

    private int getRandomInt(){
        return random.nextInt(298);
    }
}
