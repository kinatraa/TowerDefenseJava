package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.*;

public class Soldier3 extends Enemy {
    public Soldier3(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, SOLDIER3, enemyManager);
    }
}
