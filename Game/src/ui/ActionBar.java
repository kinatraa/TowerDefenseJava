package ui;

import objects.Tile;
import objects.Tower;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;

import static main.GameStates.MENU2;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar{
    private MyButton bMenu2;
    private Playing playing;
    private MyButton[] towerButtons;
    private Tower selectedTower;
    public ActionBar(int x, int y, int width, int height, Playing playing){
        super(x, y, width, height);
        this.playing = playing;
        initButtons();
    }
    private void initButtons() {
        bMenu2 = new MyButton("Menu2", 1211, 5, 64, 64);
        towerButtons = new MyButton[4];
        int yStart = 100;
        int yOffset = 40;
        int xStart = 1032;
        int xOffset = 40;
        int index = 0, cnt = 0;
        for(int i = 0; i < towerButtons.length; i++){
            towerButtons[i] = new MyButton("", xStart, yStart + yOffset * i, 32, 32, i);
        }
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
        for(MyButton b : towerButtons){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(b.getX(), b.getY(), b.getW(), b.getH());
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.getX(), b.getY(), b.getW(), b.getH(), null);
            drawButtonFeedback(g, b);
        }
    }
    public void mouseClicked(int x, int y) {
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.resetBooleans();
            SetGameState(MENU2);
        }
        else{
            for(MyButton b : towerButtons){
                b.resetBooleans();
                if(b.getBounds().contains(x, y)){
                    selectedTower = new Tower(0, 0, -1, b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu2.setMouseOver(false);
        for(MyButton b : towerButtons){
            b.setMouseOver(false);
        }
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.setMouseOver(true);
        }
        else{
            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x, y)){
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        bMenu2.setMousePressed(false);
        for(MyButton b : towerButtons){
            b.setMousePressed(false);
        }
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.setMousePressed(true);
        }
        else{
            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x, y)){
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }
    public void mouseReleased(int x, int y) {
        bMenu2.resetBooleans();
        for(MyButton b : towerButtons){
            b.resetBooleans();
        }
    }
}
