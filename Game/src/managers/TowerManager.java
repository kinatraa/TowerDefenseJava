package managers;

import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static helpz.Constants.Towers.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmout = 0;
    public TowerManager(Playing playing){
        this.playing = playing;
        loadTowerImgs();
    }
    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[4];
//        for ()
        towerImgs[0] = atlas.getSubimage(19 * 32, 10 * 32, 32, 32);
    }
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmout++, selectedTower.getTowerType()));
    }
    private BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = EnemyManager.class.getClassLoader().getResourceAsStream("elemi.png");
        try {
            if(is != null) img = ImageIO.read(is);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public void update(){

    }
    public void draw(Graphics g){
        for (Tower t : towers){
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        }
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }
    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers){
            if(t.getX() == x && t.getY() == y){
                return t;
            }
        }
        return null;
    }
}
