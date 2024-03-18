package enemies;

import helpz.Constants;

import java.awt.*;

import static helpz.Constants.Direction.*;

public abstract class Enemy {
    protected float x, y;
    protected Rectangle bounds;
    protected int health, maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;

    public Enemy(float x, float y, int ID, int enemyType) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        lastDir = RIGHT;
        setStartHealth();
    }

    private void setStartHealth() {
        health = Constants.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if (health <= 0) {
            alive = false;
        }
    }

    public void move(float speed, int dir) {
        lastDir = dir;
        switch (dir) {
            case LEFT:
                this.x -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }

        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public void setPos(int x, int y) {
        //Pos fix
        this.x = x;
        this.y = y;
    }

    public float getHealthBarFloat() {
        return health / (float) maxHealth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
        return lastDir;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
        health = 0;
    }
}
