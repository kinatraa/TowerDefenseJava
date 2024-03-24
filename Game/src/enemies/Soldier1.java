package enemies;

import managers.EnemyManager;

import static helpz.Constants.Enemies.*;

public class Soldier1 extends Enemy{

    public Soldier1(float x, float y, int ID, EnemyManager enemyManager) {
        super(x, y, ID, SOLDIER1, enemyManager);
    }
}
