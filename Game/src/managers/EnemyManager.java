package managers;

import enemies.*;
import objects.PathPoint;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CopyOnWriteArrayList;

import helpz.Utilz;

import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.*;
import static helpz.Constants.Enemies.*;
import static helpz.ImgFix.getRotImg;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();
    private PathPoint start, end;
    private int HPBarWidth = 20;
    private int[][] roadDirArr;
    private static boolean checkRoad = false;

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        this.start = start;
        this.end = end;
        enemyImgs = new BufferedImage[8];
        loadEnemyImgs();
        loadRoadDirArr();
    }

    public void loadRoadDirArr() {
        roadDirArr = Utilz.GetRoadDirArr(playing.getGame().getTileManager().getTypeArr(), start, end);
//        for(int i = 0; i < roadDirArr.length; i++){
//            for(int j = 0; j < roadDirArr[0].length; j++){
//                System.out.print(roadDirArr[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = getSpriteAtlas();
        for (int i = 0; i < 4; i++) {
            enemyImgs[i] = atlas.getSubimage(i * 32, 0, 32, 32);
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/imgs/tank01.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        enemyImgs[4] = img;
        try {
            img = ImageIO.read(new File("src/imgs/tank02.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        enemyImgs[5] = img;
    }

    private BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = EnemyManager.class.getClassLoader().getResourceAsStream("imgs/elemi.png");
        try {
            if (is != null) img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void update() {
        if(checkRoad) {
            updateWaveManager();
            for (Enemy e : enemies) {
                if (e.isAlive()) {
                    updateEnemyMove(e);
                }
            }
        }
        else {
            System.out.println("Khong tim thay duong di!");
        }
    }

    private void updateEnemyMove(Enemy e) {
        PathPoint currTile = getEnemyTile(e);
        int dir = roadDirArr[currTile.getyCord()][currTile.getxCord()];

        e.move(GetSpeed(e.getEnemyType()), dir);

        PathPoint newTile = getEnemyTile(e);

        if (!isTilesTheSame(currTile, newTile)) {
            if (isTilesTheSame(newTile, end)) {
                e.kill();
                playing.removeOneLife();
                return;
            }
            int newDir = roadDirArr[newTile.getyCord()][newTile.getxCord()];
            if (newDir != dir) {
                e.setPos(newTile.getxCord() * 32, newTile.getyCord() * 32);
                rotateEnemy(dir, newDir, e);
                e.setLastDir(newDir);
            }
        }

    }

    private void rotateEnemy(int dir, int newDir, Enemy e) {
        switch (dir) {
            case LEFT:
                if (newDir == UP) e.changeRotate(90);
                else e.changeRotate(-90);
                break;
            case RIGHT:
                if (newDir == UP) e.changeRotate(-90);
                else e.changeRotate(90);
                break;
            case UP:
                if (newDir == LEFT) e.changeRotate(-90);
                else e.changeRotate(90);
                break;
            case DOWN:
                if (newDir == LEFT) e.changeRotate(90);
                else e.changeRotate(-90);
                break;
        }
    }

    private PathPoint getEnemyTile(Enemy e) {

        switch (e.getLastDir()) {
            case LEFT:
                return new PathPoint((int) ((e.getX() + 31) / 32), (int) (e.getY() / 32));
            case UP:
                return new PathPoint((int) (e.getX() / 32), (int) ((e.getY() + 31) / 32));
            case RIGHT:
            case DOWN:
                return new PathPoint((int) (e.getX() / 32), (int) (e.getY() / 32));
        }
        return new PathPoint((int) (e.getX() / 32), (int) (e.getY() / 32));
    }

    private boolean isTilesTheSame(PathPoint currTile, PathPoint newTile) {
        if (currTile.getxCord() == newTile.getxCord() && currTile.getyCord() == newTile.getyCord())
            return true;
        return false;
    }

    private void updateWaveManager() {
        playing.getWaveManager().update();
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
    }

    public void addEnemy(int enemyTile) {
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;
        switch (enemyTile) {
            case SOLDIER1:
                enemies.add(new Soldier1(x, y, 0, this));
                break;
            case SOLDIER2:
                enemies.add(new Soldier2(x, y, 0, this));
                break;
            case SOLDIER3:
                enemies.add(new Soldier3(x, y, 0, this));
                break;
            case SOLDIER4:
                enemies.add(new Soldier4(x, y, 0, this));
                break;
            case TANK1:
                enemies.add(new Tank1(x, y, 0, this));
                break;
            case TANK2:
                enemies.add(new Tank2(x, y, 0, this));
                break;
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                drawEnemy(e, g, e.getRotate());
                drawHealthBar(e, g);
            }
        }
    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 10, getNewBarWidth(e), 3);
    }

    private int getNewBarWidth(Enemy e) {
        return (int) (HPBarWidth * e.getHealthBarFloat());
    }

    private void drawEnemy(Enemy e, Graphics g, int rotate) {
        if (e.getEnemyType() == TANK1 || e.getEnemyType() == TANK2) {
            g.drawImage(getRotImg(enemyImgs[e.getEnemyType()], rotate), (int) e.getX() - 8, (int) e.getY() - 8, 48, 48, null);
        } else {
            g.drawImage(getRotImg(enemyImgs[e.getEnemyType()], rotate), (int) e.getX() + 4, (int) e.getY() + 4, 24, 24, null);
        }
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getAmountOfAliveEnemies() {
        int cnt = 0;
        for (Enemy e : enemies) {
            if (e.isAlive()) ++cnt;
        }
        return cnt;
    }

    public void reward(int enemyType) {
        playing.reward(enemyType);
    }

    public void reset() {
        enemies.clear();
    }

    public static void setCheckRoad(boolean check) {
        checkRoad = check;
    }
}
