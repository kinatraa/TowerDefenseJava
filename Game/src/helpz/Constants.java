package helpz;

public class Constants {
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
    public static class Tiles{
        public static final int DIRT_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
        public static final int SAND_TILE = 3;
        public static final int BUSH_TILE = 4;
        public static final int ROCK_TILE = 5;
        public static final int DIRT_e_TILE = -1;
        public static final int ROAD_e_TILE = -1;
    }
    public static class Enemies{
        public static final int SOLDIER1 = 0;
        public static final int SOLDIER2 = 1;
        public static final int SOLDIER3 = 2;
        public static final int SOLDIER4 = 3;

        public static float GetSpeed(int enemyType){
            switch (enemyType){
                case SOLDIER1:
                    return 0.5f;
                case SOLDIER2:
                    return 0.65f;
                case SOLDIER3:
                    return 0.7f;
                case SOLDIER4:
                    return 0.75f;
            }
            return 0;
        }
    }
    public static class Towers{
        public static final int GREEN_CANON = 0;
        public static final int RED_CANON = 1;
        public static final int DOUBLE_ROCKET = 2;
        public static final int BIG_ROCKET = 3;
    }
}
