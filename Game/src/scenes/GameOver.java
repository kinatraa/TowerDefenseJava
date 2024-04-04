package scenes;

import main.GameWindow;
import managers.SoundManager;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods{
    private MyButton bRetry, bMenu;
    private GameWindow game;
    private SoundManager soundManager;
    public GameOver(GameWindow game) {
        super(game);
        this.game = game;
        soundManager = new SoundManager();
        initButtons();
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int y = 400;
        bMenu = new MyButton("Menu", 640-250, y, w, h);
        bRetry = new MyButton("Retry", 640+50, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        bMenu.draw(g);
        bRetry.draw(g);

        g.setFont(new Font("LucidaSans", Font.BOLD, 100));
        g.setColor(Color.RED);
        g.drawString("GAME OVER!", 330, 300);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            resetEverything();
            soundManager.selectionSound();
            SetGameState(MENU);
        }
        else if(bRetry.getBounds().contains(x, y)){
            soundManager.selectionSound();
            restartGame();
        }
    }

    @Override
    public void mouseClicked3() {

    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bRetry.setMouseOver(false);
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
        else if(bRetry.getBounds().contains(x, y)){
            bRetry.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
        else if(bRetry.getBounds().contains(x, y)){
            bRetry.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bRetry.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void restartGame() {
        resetEverything();
        SetGameState(PLAYING);
    }

    private void resetEverything(){
        game.getPlaying().resetEverything();
    }
}
