package Scenes;

import Helpz.LevelBuilder;
import Managers.TileManager;
import UI.MyButton;
import main.GameStates;
import main.GameWindow;
import main.MyMouseListener;

import java.awt.*;
import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private TileManager tileManager;
    private MyButton bMenu2;
    public Playing(GameWindow game) {
        super(game);
        initButtons();
        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
//        MyMouseListener

    }

    private void initButtons() {
        int w = 200;
        int h = w / 3;
        int x = 10;
        int y = 10;
        bMenu2 = new MyButton("Menu2", x, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*32, y*32, null);
            }
        }
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
//        System.out.println("CLICKED");
        if(bMenu2.getBounds().contains(x, y)){
            SetGameState(MENU2);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu2.setMouseOver(false);
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        bMenu2.setMousePressed(false);
        if(bMenu2.getBounds().contains(x, y)){
            bMenu2.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu2.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bMenu2.draw(g);
    }
}
