package scenes;

import managers.SoundManager;
import ui.MyButton;
import main.GameWindow;

import java.awt.*;

import static main.GameStates.*;

public class Menu2 extends GameScene implements SceneMethods{
    private MyButton bResume, bSettings, bMainMenu, bRetry, bSave;
    private Playing playing;
    private SoundManager soundManager;
    public Menu2(GameWindow game, Playing playing) {
        super(game);
        this.playing = playing;
        soundManager = new SoundManager(playing.getGame());
        initButtons();
    }
    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 100;
        int yOffset = 125;
        bResume = new MyButton("Resume", x, y, w, h);
        bRetry = new MyButton("Retry", x, y+yOffset, w, h);
        bSave = new MyButton("Save", x, y+2*yOffset, w, h);
        bSettings = new MyButton("Setting", x, y+3*yOffset, w, h);
        bMainMenu = new MyButton("Main Menu", x, y+4*yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bResume.draw(g);
        bRetry.draw(g);
        bSave.draw(g);
        bSettings.draw(g);
        bMainMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
//        System.out.println("CLICKED");
        if(bResume.getBounds().contains(x, y)){
            bResume.resetBooleans();
            soundManager.selectionSound();
            SetGameState(PLAYING);
        }
        else if(bRetry.getBounds().contains(x, y)){
            bRetry.resetBooleans();
            soundManager.selectionSound();
            playing.resetEverything();
            SetGameState(PLAYING);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.resetBooleans();
            soundManager.selectionSound();
            System.out.println("Check");
//            saveLevel();
        }
        else if(bSettings.getBounds().contains(x, y)){
            bSettings.resetBooleans();
            soundManager.selectionSound();
            getGame().getSettings().setLastGameState(MENU2);
            SetGameState(SETTINGS);
        }
        else if(bMainMenu.getBounds().contains(x, y)){
            bMainMenu.resetBooleans();
            soundManager.selectionSound();
            SetGameState(MENU);
        }
    }

    private void saveLevel() {
    }

    @Override
    public void mouseMoved(int x, int y) {
        bResume.setMouseOver(false);
        bRetry.setMouseOver(false);
        bSave.setMouseOver(false);
        bSettings.setMouseOver(false);
        bMainMenu.setMouseOver(false);
        if(bResume.getBounds().contains(x, y)){
            bResume.setMouseOver(true);
        }
        else if(bRetry.getBounds().contains(x, y)){
            bRetry.setMouseOver(true);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.setMouseOver(true);
        }
        else if(bSettings.getBounds().contains(x, y)){
            bSettings.setMouseOver(true);
        }
        if(bMainMenu.getBounds().contains(x, y)){
            bMainMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bResume.setMousePressed(false);
        bRetry.setMousePressed(false);
        bSave.setMousePressed(false);
        bSettings.setMousePressed(false);
        bMainMenu.setMousePressed(false);
        if(bResume.getBounds().contains(x, y)){
            bResume.setMousePressed(true);
        }
        else if(bRetry.getBounds().contains(x, y)){
            bRetry.setMousePressed(true);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.setMousePressed(true);
        }
        else if(bSettings.getBounds().contains(x, y)){
            bSettings.setMousePressed(true);
        }
        else if(bMainMenu.getBounds().contains(x, y)){
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
        bSave.resetBooleans();
        bSettings.resetBooleans();
        bMainMenu.resetBooleans();
    }

    @Override
    public void mouseClicked3() {

    }
}
