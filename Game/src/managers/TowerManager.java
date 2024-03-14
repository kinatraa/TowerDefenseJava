package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static helpz.Constants.Projectiles.ROCKET_BIG;
import static helpz.Constants.Projectiles.ROCKET_SMALL;
import static helpz.Constants.Towers.*;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmout = 0;
    private float rotate;
    private Enemy focusEnemy;
    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImgs();
    }

    private void loadTowerImgs() {
        BufferedImage atlas = getSpriteAtlas();
        towerImgs = new BufferedImage[4];
        towerImgs[0] = atlas.getSubimage(1 * 64, 0, 64, 64);
        towerImgs[1] = atlas.getSubimage(0 * 64, 0, 64, 64);
        towerImgs[2] = atlas.getSubimage(2 * 64, 0, 64, 64);
        towerImgs[3] = atlas.getSubimage(3 * 64, 0, 64, 64);
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmout++, selectedTower.getTowerType()));
    }

    public void newTowerDirection(Tower t, Enemy e) {
        focusEnemy = e;
        int xDist = (int) (-t.getX() + e.getX());
        int yDist = (int) (-t.getY() + e.getY());
        rotate = (float) Math.atan(yDist / (float) xDist);
        if(xDist < 0) rotate += 180;
    }

    private BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = EnemyManager.class.getClassLoader().getResourceAsStream("tower.png");
        try {
            if (is != null) img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }
    }

    private void attackEnemyIfClose(Tower t) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (isEnemyInRange(t, e)) {
                    if (t.isCooldownOver()) {
                        playing.shootEnemy(t, e);
                        t.resetCooldown();
                    }
                } else {

                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }

    public void draw(Graphics g) {
        for (Tower t : towers) {
            g.drawImage(towerImgs[t.getTowerType()], t.getX() - 8, t.getY() - 8, 48, 48, null);
        }
        //rotate
//        Graphics2D g2d = (Graphics2D) g;
//        for(Tower t : towers){
//            AffineTransform originalTransform = g2d.getTransform();
//
//            if(focusEnemy != null && isEnemyInRange(t, focusEnemy)) {
//                g2d.translate(t.getX() + 16, t.getY() + 16);
//                g2d.rotate(rotate + 45);
//                g2d.drawImage(towerImgs[t.getTowerType()], -24, -24, 48, 48, null);
//                g2d.setTransform(originalTransform);
//            } else {
//                g2d.drawImage(towerImgs[t.getTowerType()], t.getX() - 8, t.getY() - 8, 48, 48, null);
//            }
//        }
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }
}
