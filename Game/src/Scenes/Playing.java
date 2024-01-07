package Scenes;

import Helpz.LevelBuilder;
import Managers.TileManager;
import main.GameWindow;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private TileManager tileManager;
    public Playing(GameWindow game) {
        super(game);
        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();
    }

    @Override
    public void render(Graphics g) {
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*64, y*64, null);
            }
        }
    }
}
