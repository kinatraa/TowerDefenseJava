package scenes;

import main.Game;
import managers.SoundManager;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Victory extends GameScene implements SceneMethods{
    private MyButton bMenu;
    private Game game;
    private SoundManager soundManager;
    public Victory(Game game) {
        super(game);
        this.game = game;
        soundManager = game.getSoundManager();
        initButtons();
    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int y = 400;
        bMenu = new MyButton("Menu", 640-100, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        bMenu.draw(g);

        g.setFont(new Font("LucidaSans", Font.BOLD, 100));
        g.setColor(Color.RED);
        g.drawString("VICTORY!", 400, 300);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            resetEverything();
            soundManager.selectionSound(soundManager.getGainEffect());
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseClicked3() {

    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
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
