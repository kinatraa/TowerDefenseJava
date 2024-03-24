package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.*;

public class Soldier4 extends Enemy {
    public Soldier4(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, SOLDIER4, enemyManager);
    }
}
