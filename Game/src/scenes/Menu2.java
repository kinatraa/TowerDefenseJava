package scenes;

import managers.SoundManager;
import ui.MyButton;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.*;

public class Menu2 extends GameScene implements SceneMethods {
    private MyButton bResume, bSettings, bMainMenu, bRetry;
    private Playing playing;
    private SoundManager soundManager;
    private BufferedImage background;

    public Menu2(Game game, Playing playing) {
        super(game);
        this.playing = playing;
        soundManager = playing.getGame().getSoundManager();
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
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 130;
        int yOffset = 125;
        bResume = new MyButton("Resume", x, y, w, h);
        bRetry = new MyButton("Retry", x, y + yOffset, w, h);
        bSettings = new MyButton("Setting", x, y + 2 * yOffset, w, h);
        bMainMenu = new MyButton("Main Menu", x, y + 3 * yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(background.getGraphics());
        g.drawImage(background, 0, 0, null);
    }

    private void drawButtons(Graphics g) {
        bResume.draw(g);
        bRetry.draw(g);
        bSettings.draw(g);
        bMainMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
//        System.out.println("CLICKED");
        if (bResume.getBounds().contains(x, y)) {
            bResume.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            SetGameState(PLAYING);
        } else if (bRetry.getBounds().contains(x, y)) {
            bRetry.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            playing.resetEverything();
            SetGameState(PLAYING);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            getGame().getSettings().setLastGameState(MENU2);
            SetGameState(SETTINGS);
        } else if (bMainMenu.getBounds().contains(x, y)) {
            bMainMenu.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            SetGameState(MENU);
        }
    }

    private void saveLevel() {
    }

    @Override
    public void mouseMoved(int x, int y) {
        bResume.setMouseOver(false);
        bRetry.setMouseOver(false);
        bSettings.setMouseOver(false);
        bMainMenu.setMouseOver(false);
        if (bResume.getBounds().contains(x, y)) {
            bResume.setMouseOver(true);
        } else if (bRetry.getBounds().contains(x, y)) {
            bRetry.setMouseOver(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        }
        if (bMainMenu.getBounds().contains(x, y)) {
            bMainMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bResume.setMousePressed(false);
        bRetry.setMousePressed(false);
        bSettings.setMousePressed(false);
        bMainMenu.setMousePressed(false);
        if (bResume.getBounds().contains(x, y)) {
            bResume.setMousePressed(true);
        } else if (bRetry.getBounds().contains(x, y)) {
            bRetry.setMousePressed(true);
        } else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMousePressed(true);
        } else if (bMainMenu.getBounds().contains(x, y)) {
            bMainMenu.setMousePressed(true);
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
        bResume.resetBooleans();
        bRetry.resetBooleans();
        bSettings.resetBooleans();
        bMainMenu.resetBooleans();
    }

    @Override
    public void mouseClicked3() {

    }
}
