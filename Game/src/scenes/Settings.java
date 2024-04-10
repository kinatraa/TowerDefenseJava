package scenes;

import main.GameWindow;
import ui.MyButton;

import javax.swing.*;
import java.awt.*;

public class Settings extends GameScene implements SceneMethods{
    private MyButton[] bGainMusic, bGainEffect;
    private int gainMusic, gainEffect;
    public Settings(GameWindow game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        bGainMusic = new MyButton[2];
        bGainEffect = new MyButton[2];
        int x = 600, w = 50, h = 50;
        int y = 250;
        int xOffset = 300;
        bGainMusic[0] = new MyButton("-", x, y, w, h);
        bGainMusic[1] = new MyButton("+", x + xOffset, y, w, h);
        bGainEffect[0] = new MyButton("-", x, y + 100, w, h);
        bGainEffect[1] = new MyButton("+", x + xOffset, y + 100, w, h);
    }

    private void drawButtons(Graphics g) {
        for(int i = 0; i < 2; i++){
            bGainMusic[i].draw(g);
            bGainEffect[i].draw(g);
        }
    }

    private void drawGainInfo(Graphics g) {
        g.drawString("" + gainMusic, 780, 275);
        g.drawString("" + gainEffect, 780, 375);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
        drawGainInfo(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bGainMusic[0].getBounds().contains(x, y)) {
            if(gainMusic - 10 >= 0){
                gainMusic -= 10;
            }
        } else if (bGainMusic[1].getBounds().contains(x, y)) {
            if(gainMusic + 10 <= 100){
                gainMusic += 10;
            }
        } else if (bGainEffect[0].getBounds().contains(x, y)) {
            if(gainEffect - 10 >= 0){
                gainEffect -= 10;
            }
        } else if (bGainEffect[1].getBounds().contains(x, y)) {
            if(gainEffect + 10 <= 100){
                gainEffect += 10;
            }
        }
    }
    @Override
    public void mouseClicked3() {

    }
    @Override
    public void mouseMoved(int x, int y) {
        bGainMusic[0].setMouseOver(false);
        bGainMusic[1].setMouseOver(false);
        bGainEffect[0].setMouseOver(false);
        bGainEffect[1].setMouseOver(false);
        if (bGainMusic[0].getBounds().contains(x, y)) {
            bGainMusic[0].setMouseOver(true);
        } else if (bGainMusic[1].getBounds().contains(x, y)) {
            bGainMusic[1].setMouseOver(true);
        } else if (bGainEffect[0].getBounds().contains(x, y)) {
            bGainEffect[0].setMouseOver(true);
        } else if (bGainEffect[1].getBounds().contains(x, y)) {
            bGainEffect[1].setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bGainMusic[0].setMousePressed(false);
        bGainMusic[1].setMousePressed(false);
        bGainEffect[0].setMousePressed(false);
        bGainEffect[1].setMousePressed(false);
        if (bGainMusic[0].getBounds().contains(x, y)) {
            bGainMusic[0].setMousePressed(true);
        } else if (bGainMusic[1].getBounds().contains(x, y)) {
            bGainMusic[1].setMousePressed(true);
        } else if (bGainEffect[0].getBounds().contains(x, y)) {
            bGainEffect[0].setMousePressed(true);
        } else if (bGainEffect[1].getBounds().contains(x, y)) {
            bGainEffect[1].setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButton();
    }

    private void resetButton() {
        for(int i = 0; i < 2; i++){
            bGainMusic[i].resetBooleans();
            bGainEffect[i].resetBooleans();
        }
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
