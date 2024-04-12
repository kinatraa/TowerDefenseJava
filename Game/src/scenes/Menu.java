package scenes;

import managers.SoundManager;
import ui.MyButton;
import main.GameWindow;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
    private MyButton bPlaying, bSettings, bQuit, bEdit;
    private SoundManager soundManager;

    public Menu(GameWindow game) {
        super(game);
        soundManager = new SoundManager(game);
        initButtons();
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 100;
        int yOffset = 125;
        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Edit", x, y + yOffset, w, h);
        bSettings = new MyButton("Setting", x, y + 2 * yOffset, w, h);
        bQuit = new MyButton("Quit", x, y + 3 * yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        drawButtons(g);
    }

    private void drawBackground(Graphics g) {
        BufferedImage bg = null;
        InputStream is = Menu.class.getClassLoader().getResourceAsStream("canhdongvotree.png");
        try {
            if (is != null) bg = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(bg, 0, 0, 1280, 768, null);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.resetBooleans();
            getGame().getPlaying().resetEverything();
            soundManager.selectionSound();
            SetGameState(PLAYING);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.resetBooleans();
            soundManager.selectionSound();
            SetGameState(EDIT);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.resetBooleans();
            soundManager.selectionSound();
            getGame().getSettings().setLastGameState(MENU);
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.resetBooleans();
            soundManager.selectionSound();
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked3() {

    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bPlaying.setMousePressed(false);
        bEdit.setMousePressed(false);
        bSettings.setMousePressed(false);
        bQuit.setMousePressed(false);
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMousePressed(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
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
}
