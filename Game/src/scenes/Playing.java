package scenes;

import helpz.LoadSave;
import managers.EnemyManager;
import ui.MyButton;
import main.GameWindow;
import ui.ActionBar;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Playing extends GameScene implements SceneMethods, ImageObserver {
    private int[][] lvl;
    private ActionBar actionBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    public Playing(GameWindow game) {
        super(game);
        loadDefaultLevel();
        actionBar = new ActionBar(1024, 0, 256, 768, this);
        enemyManager = new EnemyManager(this);
    }
    public void update(){
        enemyManager.update();
    }
    public void setLevel(int[][] lvl){
        this.lvl = lvl;
    }
    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
    }
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
    }
    private void drawLevel(Graphics g){
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x*32, y*32, null);
            }
        }
    }
    private BufferedImage getSprite(int spiteID){
        return getGame().getTileManager().getSprite(spiteID);
    }
    @Override
    public void mouseClicked(int x, int y) {
        if(x > 1024){
            actionBar.mouseClicked(x, y);
        }
        else{
            enemyManager.addEnemy(x, y);
        }
    }
    @Override
    public void mouseClicked3() {

    }
    @Override
    public void mouseMoved(int x, int y) {
        if(x > 1024){
            actionBar.mouseMoved(x, y);
        }
        else{
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(x > 1024){
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
