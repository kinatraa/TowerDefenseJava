package ui;

import scenes.Playing;

import javax.swing.*;
import java.awt.*;

import static main.GameStates.MENU2;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar{
    private MyButton bMenu2;
    private Playing playing;
    public ActionBar(int x, int y, int width, int height, Playing playing){
        super(x, y, width, height);
        this.playing = playing;
        initButtons();
    }
    private void initButtons() {
        bMenu2 = new MyButton("Menu2", 1211, 5, 64, 64);
    }
    public void draw(Graphics g){
        g.setColor(new Color(255,201,215));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    private void drawButtons(Graphics g){
        ImageIcon imageIcon = new ImageIcon("src/gear.png");
        Image image = imageIcon.getImage();
        g.drawImage(image, 1211, 5, null);
    }
    public void mouseClicked(int x, int y) {
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.resetBooleans();
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
