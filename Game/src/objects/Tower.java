package objects;

import helpz.Constants;

import static helpz.Constants.Towers.*;

public class Tower {
    private int x, y, id, towerType, cdTick, dmg, tier;
    private float range, cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        tier = 1;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    public void upgradeTower() {
        ++this.tier;
        switch (towerType) {
            case GREEN_CANON:
                dmg += 5;
                range += 20;
                break;
            case RED_CANON:
                dmg += 10;
                range += 20;
                break;
            case DOUBLE_RR:
                dmg += 15;
                range += 10;
                break;
            case BIG_ONE:
                dmg += 20;
                range += 10;
                break;
        }
    }

    public void update() {
        cdTick++;
    }

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
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

    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public int getTier() {
        return tier;
    }
}
