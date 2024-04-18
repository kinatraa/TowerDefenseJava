package scenes;

import managers.SoundManager;
import ui.MyButton;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
    private MyButton bPlaying, bSettings, bQuit, bEdit;
    private SoundManager soundManager;
    private BufferedImage background = null, logo = null;

    public Menu(Game game) {
        super(game);
        soundManager = game.getSoundManager();
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
        try {
            logo = ImageIO.read(new File("src/imgs/logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x1 = 1280 / 2 - 150 - w / 2;
        int x2 = 1280 / 2 + 150 - w / 2;
        int y = 350;
        int yOffset = 125;
        bPlaying = new MyButton("Play", x1, y, w, h);
        bEdit = new MyButton("Edit", x2, y, w, h);
        bSettings = new MyButton("Setting", x1, y + yOffset, w, h);
        bQuit = new MyButton("Quit", x2, y + yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        draw(background.getGraphics());
        g.drawImage(background, 0, 0, null);
    }

    private void draw(Graphics g) {
        drawButtons(g);
        drawLogo(g);
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    private void drawLogo(Graphics g) {
        g.drawImage(logo, 1280/2 - logo.getWidth()/2, 0, null);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.resetBooleans();
            getGame().getPlaying().resetEverything();
            soundManager.selectionSound(soundManager.getGainEffect());
            SetGameState(PLAYING);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            SetGameState(EDIT);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            getGame().getSettings().setLastGameState(MENU);
            SetGameState(SETTINGS);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
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
}
