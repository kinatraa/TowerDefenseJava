package managers;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;

public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;
    private int exploTick, exploIndex;
    private Point2D.Float exploPos;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        proj_imgs = new BufferedImage[4];
        proj_imgs[0] = atlas.getSubimage(19 * 32, 11 * 32, 32, 32);
        proj_imgs[1] = atlas.getSubimage(20 * 32, 11 * 32, 32, 32);
        proj_imgs[2] = atlas.getSubimage(21 * 32, 10 * 32, 32, 32);
        proj_imgs[3] = atlas.getSubimage(22 * 32, 10 * 32, 32, 32);
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);
        int xDist = (int) (t.getX() - e.getX());
        int yDist = (int) (t.getY() - e.getY());
        int totalDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / totalDist;
        float yPer = 1f - xPer;

        float xSpeed = xPer * Constants.Projectiles.GetSpeed(type);
        float ySpeed = Constants.Projectiles.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX()) {
            xSpeed *= -1;
        }
        if (t.getY() > e.getY()) {
            ySpeed *= -1;
        }
        float rotate = 0;
        if(type == ROCKET_BIG || type == ROCKET_SMALL){
            float arcValue = (float) Math.atan(yDist / (float) xDist);
            rotate = (float) Math.toDegrees(arcValue);
            if(xDist < 0) rotate += 180;
        }
        projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));
    }

    public void update() {
        for (Projectile p : projectiles) {
            if (p.isActive()) {
                p.move();
                if (isProjHittingEnemy(p)) {
                    p.setActive(false);
                    if(p.getProjectileType() == ROCKET_SMALL || p.getProjectileType() == ROCKET_BIG){
                        explodeOnEnemies(p);
                    }
                } else {

                }
            }
        }
    }

    private void explodeOnEnemies(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if(e.isAlive()){
                float radius = 40.0f;
                float xDist = Math.abs(p.getPos().x - e.getX());
                float yDist = Math.abs(p.getPos().y - e.getY());
                float realDist = (float) Math.hypot(xDist, yDist);
                if(realDist <= radius) e.hurt(p.getDmg());
            }
        }
    }

    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if(e.isAlive()){
                if (e.getBounds().contains(p.getPos())) {
                    e.hurt(p.getDmg());
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Projectile p : projectiles) {
            if (p.isActive()){
                if(p.getProjectileType() == ROCKET_BIG || p.getProjectileType() == ROCKET_SMALL){
                    g2d.translate(p.getPos().x, p.getPos().y);
                    g2d.rotate(Math.toRadians(p.getRotation() - 90));
                    g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
                    g2d.rotate(-Math.toRadians(p.getRotation() - 90));
                    g2d.translate(-p.getPos().x, -p.getPos().y);
                }
                else{
                    g2d.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x - 16, (int) p.getPos().y - 16, null);
                }
            }
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

//    public class Explosion{
//        private Point2D.Float pos;
//
//        public Explosion(Point2D.Float pos){
//            this.pos = pos;
//        }
//        public void update(){
//
//        }
//        public Point2D.Float getPos(){
//            return pos;
//        }
//    }
}
