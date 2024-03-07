package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            proj_imgs[i] = atlas.getSubimage((19 + i) * 32, 11 * 32, 32, 32);
        }
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int xDist = (int) Math.abs(t.getX() - e.getX());
        int yDist = (int) Math.abs(t.getY() - e.getY());
        int totalDist = xDist + yDist;

        float xPer = (float) xDist / totalDist;
        float yPer = 1f - xPer;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *= -1;
        }
        if (t.getY() > e.getY()) {
            ySpeed *= -1;
        }
        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), proj_id++, type));
    }

    public void update() {
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                p.move();
                if (isProjHittingEnemy(p)) {
                    p.setActive(false);
                } else {

                }
            }
        }
    }

    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        for (Projectile p : projectiles) {
            if (p.isActive())
                g.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x, (int) p.getPos().y, null);
        }
    }

    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case GREEN_CANON:
                return BULLET_GREEN;
            case RED_CANON:
                return BULLET_RED;
            case DOUBLE_RR:
                return ROCKET_SMALL;
            case BIG_ONE:
                return ROCKET_BIG;
        }
        return -1;
    }
}
