package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.TANK1;

public class Tank1 extends Enemy {
    public Tank1(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, TANK1, enemyManager);
    }
}
