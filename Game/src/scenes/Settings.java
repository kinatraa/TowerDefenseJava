package scenes;

import main.GameStates;
import main.Game;
import managers.SoundManager;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.SetGameState;

public class Settings extends GameScene implements SceneMethods{
    private MyButton[] bGainMusic, bGainEffect;
    private MyButton bReturn;
    private int gainMusic = 0, gainEffect = 0;
    private GameStates lastGameState;
    private Game game;
    private BufferedImage background;

    public Settings(Game game) {
        super(game);
        this.game = game;
        initButtons();
        importImgs();
    }

    private void importImgs() {
        InputStream is = Menu.class.getClassLoader().getResourceAsStream("imgs/background_maybe.png");
        try {
            if (is != null) background = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initButtons() {
        bGainMusic = new MyButton[2];
        bGainEffect = new MyButton[2];
        int x = 550, w = 50, h = 50;
        int y = 250;
        int xOffset = 300;
        bGainMusic[0] = new MyButton("-", x, y, w, h);
        bGainMusic[1] = new MyButton("+", x + xOffset, y, w, h);
        bGainEffect[0] = new MyButton("-", x, y + 100, w, h);
        bGainEffect[1] = new MyButton("+", x + xOffset, y + 100, w, h);
        bReturn = new MyButton("Return", 600, 500, 100, h);
    }

    private void drawButtons(Graphics g) {
        g.setFont(new Font("Impact", Font.PLAIN, 30));
        g.drawString("Music: ", 400, 280);
        g.drawString("Effect: ", 400, 380);
        for(int i = 0; i < 2; i++){
            bGainMusic[i].draw(g);
            bGainEffect[i].draw(g);
        }
        bReturn.draw(g);
    }

    private void drawGainInfo(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Impact", Font.PLAIN, 30));
        g.drawString("" + gainMusic, 710, 290);
        g.drawString("" + gainEffect, 710, 390);
    }

    private void draw(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void render(Graphics g) {
        draw(background.getGraphics());
        g.drawImage(background, 0, 0, null);
        drawGainInfo(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bGainMusic[0].getBounds().contains(x, y)) {
            game.getSoundManager().selectionSound(game.getSoundManager().getGainEffect());
            if(gainMusic - 10 >= 0){
                gainMusic -= 10;
            }
        } else if (bGainMusic[1].getBounds().contains(x, y)) {
            game.getSoundManager().selectionSound(game.getSoundManager().getGainEffect());
            if(gainMusic + 10 <= 100){
                gainMusic += 10;
            }
        } else if (bGainEffect[0].getBounds().contains(x, y)) {
            game.getSoundManager().selectionSound(game.getSoundManager().getGainEffect());
            if(gainEffect - 10 >= 0){
                gainEffect -= 10;
            }
        } else if (bGainEffect[1].getBounds().contains(x, y)) {
            game.getSoundManager().selectionSound(game.getSoundManager().getGainEffect());
            if(gainEffect + 10 <= 100){
                gainEffect += 10;
            }
        }
        else if(bReturn.getBounds().contains(x, y)) {
            game.getSoundManager().selectionSound(game.getSoundManager().getGainEffect());
            SetGameState(lastGameState);
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
        bReturn.setMouseOver(false);
        if (bGainMusic[0].getBounds().contains(x, y)) {
            bGainMusic[0].setMouseOver(true);
        } else if (bGainMusic[1].getBounds().contains(x, y)) {
            bGainMusic[1].setMouseOver(true);
        } else if (bGainEffect[0].getBounds().contains(x, y)) {
            bGainEffect[0].setMouseOver(true);
        } else if (bGainEffect[1].getBounds().contains(x, y)) {
            bGainEffect[1].setMouseOver(true);
        }
        else if (bReturn.getBounds().contains(x, y)) {
            bReturn.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bGainMusic[0].setMousePressed(false);
        bGainMusic[1].setMousePressed(false);
        bGainEffect[0].setMousePressed(false);
        bGainEffect[1].setMousePressed(false);
        bReturn.setMousePressed(false);
        if (bGainMusic[0].getBounds().contains(x, y)) {
            bGainMusic[0].setMousePressed(true);
        } else if (bGainMusic[1].getBounds().contains(x, y)) {
            bGainMusic[1].setMousePressed(true);
        } else if (bGainEffect[0].getBounds().contains(x, y)) {
            bGainEffect[0].setMousePressed(true);
        } else if (bGainEffect[1].getBounds().contains(x, y)) {
            bGainEffect[1].setMousePressed(true);
        }
        else if(bReturn.getBounds().contains(x, y)){
            bReturn.setMousePressed(true);
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

    public int getGainMusic() {
        return gainMusic;
    }

    public int getGainEffect() {
        return gainEffect;
    }

    public void setLastGameState(GameStates lastGameState) {
        this.lastGameState = lastGameState;
    }
}
