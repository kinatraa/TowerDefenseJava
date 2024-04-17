package managers;

import enemies.Enemy;
import objects.Tower;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private Vector<Tower> towers = new Vector<>();
    private int towerAmout = 0;
    private float angle, rotate;
    private Enemy target;

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

    public void trackingEnemy(Tower t, Enemy e) {
        target = e;
    }

    private BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = EnemyManager.class.getClassLoader().getResourceAsStream("imgs/tower.png");
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
            if (isEnemyInRange(t, e) && e.isAlive()) {
                if (t.isCooldownOver()) {
                    playing.shootEnemy(t, e);
                    t.resetCooldown();
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return range < t.getRange();
    }

    public void draw(Graphics g) {
        //rotate
        Graphics2D g2d = (Graphics2D) g;
        for (Tower t : towers) {
            if (target != null && isEnemyInRange(t, target) && target.isAlive()) {
                angle = (float) Math.atan2(target.getY() - t.getY(), target.getX() - t.getX());
                rotate = (float) Math.toDegrees(angle) + 87;
                g2d.translate(t.getX() + 16, t.getY() + 16);
                g2d.rotate(Math.toRadians(rotate));
                g2d.drawImage(towerImgs[t.getTowerType()], -32, -32, 64, 64, null);
                g2d.rotate(-Math.toRadians(rotate));
                g2d.translate(-(t.getX() + 16), -(t.getY() + 16));
            } else {
                g2d.drawImage(towerImgs[t.getTowerType()], t.getX() - 18, t.getY() - 18, 64, 64, null);
            }
        }
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

    public void removeTower(Tower displayedTower) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getId() == displayedTower.getId()) towers.remove(i);
        }
    }

    public void upgradeTower(Tower displayedTower) {
        for (Tower t : towers) {
            if (t.getId() == displayedTower.getId()) {
                t.upgradeTower();
            }
        }
    }

    public void reset(){
        towers.clear();
        towerAmout = 0;
    }
}
