package ui;

import objects.Tile;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU2;
import static main.GameStates.SetGameState;

public class ToolBar {
    private int x, y, width, height;
    private MyButton bMenu2;
    private Playing playing;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    public ToolBar(int x, int y, int width, int height, Playing playing){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;
        initButtons();
    }
    private void initButtons() {
        bMenu2 = new MyButton("Menu2", 1211, 5, 64, 64);
        int yStart = 100;
        int yOffset = 74;
        int i = 0;
        for(Tile tile: playing.getTileManager().tiles){
            tileButtons.add(new MyButton(tile.getName(), 1032, yStart + yOffset*i, 64, 64, i));
            ++i;
        }
    }
    public void draw(Graphics g){
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    private void drawButtons(Graphics g){
        ImageIcon imageIcon = new ImageIcon("src/gear.png");
        Image image = imageIcon.getImage();
        g.drawImage(image, 1211, 5, null);

        for(MyButton b : tileButtons){
            g.drawImage(getButtonImg(b.getId()), b.getX(), b.getY(), b.getW(), b.getH(), null);
        }
    }

    private BufferedImage getButtonImg(int id) {
        return playing.getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
//        System.out.println("CLICKED");
        if(bMenu2.getBounds().contains(x, y)){
            SetGameState(MENU2);
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu2.setMouseOver(false);
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        bMenu2.setMousePressed(false);
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu2.resetBooleans();
    }
}
