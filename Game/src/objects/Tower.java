package objects;

import helpz.Constants;

public class Tower {
    private int x, y, id, towerType;
    private float dmg, range, cooldown;
    public Tower(int x, int y, int id, int towerType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    private void setDefaultCooldown() {
        cooldown = Constants.Towers.GetDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
        range = Constants.Towers.GetDefaultRange(towerType);
    }

    private void setDefaultDmg() {
        dmg = Constants.Towers.GetDefaultDmg(towerType);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public float getCooldown() {
        return cooldown;
    }

    public float getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }
}
