package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.*;

public class Soldier2 extends Enemy {
    public Soldier2(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, SOLDIER2, enemyManager);
    }
}
