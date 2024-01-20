package scenes;

import ui.MyButton;
import main.GameWindow;

import java.awt.*;

import static main.GameStates.*;

public class Menu2 extends GameScene implements SceneMethods{
    private MyButton bResume, bSettings, bMainMenu, bRestart;
    public Menu2(GameWindow game) {
        super(game);
        initButtons();
    }
    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x = 1280 / 2 - w / 2;
        int y = 150;
        int yOffset = 125;
        bResume = new MyButton("Resume", x, y, w, h);
        bRestart = new MyButton("Restart", x, y+yOffset, w, h);
        bSettings = new MyButton("Setting", x, y+2*yOffset, w, h);
        bMainMenu = new MyButton("Main Menu", x, y+3*yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(0, 0, 1280, 768);
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bResume.draw(g);
        bRestart.draw(g);
        bSettings.draw(g);
        bMainMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
//        System.out.println("CLICKED");
        if(bResume.getBounds().contains(x, y)){
            SetGameState(PLAYING);
        }
        else if(bRestart.getBounds().contains(x, y)){
//            restart();
        }
        else if(bSettings.getBounds().contains(x, y)){
            SetGameState(SETTINGS);
        }
        else if(bMainMenu.getBounds().contains(x, y)){
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bResume.setMouseOver(false);
        bRestart.setMouseOver(false);
        bSettings.setMouseOver(false);
        bMainMenu.setMouseOver(false);
        if(bResume.getBounds().contains(x, y)){
            bResume.setMouseOver(true);
        }
        else if(bRestart.getBounds().contains(x, y)){
            bRestart.setMouseOver(true);
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
        bRestart.setMousePressed(false);
        bSettings.setMousePressed(false);
        bMainMenu.setMousePressed(false);
        if(bResume.getBounds().contains(x, y)){
            bResume.setMousePressed(true);
        }
        else if(bRestart.getBounds().contains(x, y)){
            bRestart.setMousePressed(true);
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

    private void resetButtons() {
        bResume.resetBooleans();
        bRestart.resetBooleans();
        bSettings.resetBooleans();
        bMainMenu.resetBooleans();
    }
}
