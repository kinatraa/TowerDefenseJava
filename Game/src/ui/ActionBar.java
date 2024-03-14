package ui;

import helpz.Constants;
import objects.Tile;
import objects.Tower;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;

import static main.GameStates.MENU2;
import static main.GameStates.SetGameState;
import static helpz.Constants.Towers.*;

public class ActionBar extends Bar{
    private MyButton bMenu2;
    private Playing playing;
    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;
    public ActionBar(int x, int y, int width, int height, Playing playing){
        super(x, y, width, height);
        this.playing = playing;
        initButtons();
    }
    private void initButtons() {
        bMenu2 = new MyButton("Menu2", 1211, 5, 64, 64);
        towerButtons = new MyButton[4];
        int yStart = 100;
        int yOffset = 80;
        int xStart = 1032;
        int xOffset = 40;
        int index = 0, cnt = 0;
        for(int i = 0; i < towerButtons.length; i++){
            towerButtons[i] = new MyButton("", xStart, yStart + yOffset * i, 64, 64, i);
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
        drawButtons(g);
        drawDisplayedTower(g);
    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower != null){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(1028, 650, 220, 85);
            g.setColor(Color.BLACK);
            g.drawRect(1028, 650, 220, 85);
            g.drawRect(1035, 660, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 1035, 660, 50, 50, null);
            g.setFont(new Font("LucidaSans", Font.BOLD, 15));
            g.drawString("" + Constants.Towers.GetName(displayedTower.getTowerType()), 1100, 680);
            g.drawString("ID: " + displayedTower.getId(), 1100, 700);

            drawDisplayedTowerBorder(g);
            drawDisplayedTowerRange(g);
        }
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawOval(displayedTower.getX() + 16 - (int)displayedTower.getRange()*2/2, displayedTower.getY() + 16 - (int)displayedTower.getRange()*2/2, (int)displayedTower.getRange()*2, (int)displayedTower.getRange()*2);
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    public void displayTower(Tower t){
        displayedTower = t;
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
