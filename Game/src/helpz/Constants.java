package helpz;

public class Constants {
    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Tiles {
        public static final int DIRT_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
        public static final int SAND_TILE = 3;
        public static final int BUSH_TILE = 4;
        public static final int ROCK_TILE = 5;
        public static final int DIRT_e_TILE = -1;
        public static final int ROAD_e_TILE = -1;
    }

    public static class Enemies {
        public static final int SOLDIER1 = 0;
        public static final int SOLDIER2 = 1;
        public static final int SOLDIER3 = 2;
        public static final int SOLDIER4 = 3;
        public static final int TANK1 = 4;
        public static final int TANK2 = 5;

        public static int GetReward(int enemyType) {
            switch (enemyType) {
                case SOLDIER1:
                    return 5;
                case SOLDIER2:
                    return 10;
                case SOLDIER3:
                    return 15;
                case SOLDIER4:
                    return 20;
                case TANK1:
                    return 30;
                case TANK2:
                    return 40;
            }
            return 0;
        }

        public static float GetSpeed(int enemyType) {
            switch (enemyType) {
                case SOLDIER1:
                    return 0.5f;
                case SOLDIER2:
                    return 0.65f;
                case SOLDIER3:
                    return 0.7f;
                case SOLDIER4:
                    return 0.75f;
                case TANK1:
                    return 0.5f;
                case TANK2:
                    return 0.65f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {
            switch (enemyType) {
                case SOLDIER1:
                    return 50;
                case SOLDIER2:
                    return 80;
                case SOLDIER3:
                    return 100;
                case SOLDIER4:
                    return 200;
                case TANK1:
                    return 300;
                case TANK2:
                    return 400;
            }
            return 0;
        }
    }

    public static class Towers {
        public static final int GREEN_CANON = 0;
        public static final int RED_CANON = 1;
        public static final int DOUBLE_RR = 2;
        public static final int BIG_ONE = 3;

        public static int GetCost(int towerType) {
            switch (towerType) {
                case GREEN_CANON:
                    return 50;
                case RED_CANON:
                    return 70;
                case DOUBLE_RR:
                    return 100;
                case BIG_ONE:
                    return 150;
            }
            return 0;
        }

        public static String GetName(int towerType) {
            switch (towerType) {
                case GREEN_CANON:
                    return "Green Canon";
                case RED_CANON:
                    return "Red Canon";
                case DOUBLE_RR:
                    return "Double Rocket";
                case BIG_ONE:
                    return "Big Rocket";
            }
            return "";
        }

        public static int GetDefaultDmg(int towerType) {
            switch (towerType) {
                case GREEN_CANON:
                    return 10;
                case RED_CANON:
                    return 15;
                case DOUBLE_RR:
                    return 25;
                case BIG_ONE:
                    return 40;
            }
            return 0;
        }

        public static float GetDefaultRange(int towerType) {
            switch (towerType) {
                case GREEN_CANON:
                    return 150;
                case RED_CANON:
                    return 150;
                case DOUBLE_RR:
                    return 200;
                case BIG_ONE:
                    return 200;
            }
            return 0;
        }

        public static float GetDefaultCooldown(int towerType) {
            switch (towerType) {
                case GREEN_CANON, RED_CANON:
                    return 40;
                case DOUBLE_RR:
                    return 100;
                case BIG_ONE:
                    return 125;
            }
            return 0;
        }
    }

    public static class Projectiles {
        public static final int BULLET_GREEN = 0;
        public static final int BULLET_RED = 1;
        public static final int ROCKET_SMALL = 2;
        public static final int ROCKET_BIG = 3;

        public static float GetSpeed(int type) {
            switch (type) {
                case BULLET_GREEN, BULLET_RED:
                    return 8;
                case ROCKET_SMALL:
                    return 6;
                case ROCKET_BIG:
                    return 6;
            }
            return 0;
        }
    }
}
