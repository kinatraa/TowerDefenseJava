package scenes;

import helpz.LevelBuilder;
import managers.TileManager;
import ui.MyButton;
import main.GameWindow;
import ui.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods, ImageObserver {
    private int[][] lvl;
    private TileManager tileManager;

    private ToolBar toolBar;
    public Playing(GameWindow game) {
        super(game);
        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
//        MyMouseListener
        toolBar = new ToolBar(1024, 0, 256, 768, this);
    }

    @Override
    public void render(Graphics g) {
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*32, y*32, null);
            }
        }
        toolBar.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(x >= 1211 && y <= 69){
            toolBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(x >= 1211 && y <= 69){
            toolBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(x >= 1211 && y <= 69){
            toolBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolBar.mouseReleased(x, y);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
