package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.TANK2;

public class Tank2 extends Enemy{
    public Tank2(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, TANK2, enemyManager);
    }
}
