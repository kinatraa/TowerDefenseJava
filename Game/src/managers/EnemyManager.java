package managers;

import enemies.*;
import helpz.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.*;
import static helpz.Constants.Enemies.*;
import static helpz.ImgFix.getRotImg;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private PathPoint start, end;
    private int HPBarWidth = 20;
    public EnemyManager(Playing playing, PathPoint start, PathPoint end){
        this.playing = playing;
        this.start = start;
        this.end = end;
        enemyImgs = new BufferedImage[8];
        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = getSpriteAtlas();
        for(int i = 0; i < 4; i++){
            enemyImgs[i] = atlas.getSubimage(i*32, 0, 32, 32);
        }
    }
    private BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = EnemyManager.class.getClassLoader().getResourceAsStream("elemi.png");
        try {
            if(is != null) img = ImageIO.read(is);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public void update(){
        updateWaveManager();
        for(Enemy e : enemies){
            if(e.isAlive()){
                updateEnemyMove(e);
            }
        }
    }

    private void updateWaveManager() {
        playing.getWaveManager().update();
    }

    private void updateEnemyMove(Enemy e) {
        if(e.getLastDir() == -1){
            setNewDirectionAndMove(e);
        }

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

        if(getTileType(newX, newY) == DIRT_TILE){
            //keep moving
            e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
        }
        else if(isAtEnd(e)){
            //reached the end
            e.kill();
        }
        else{
            //find new direction
            setNewDirectionAndMove(e);
        }
    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();
        int xCord = (int) (e.getX() / 32);
        int yCord = (int) (e.getY() / 32);
        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if(isAtEnd(e)) return;

        if(dir == LEFT || dir == RIGHT){
            int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
            if(getTileType((int)e.getX(), newY) == DIRT_TILE){
                if(dir == LEFT) e.changeRotate(90);
                else e.changeRotate(-90);
                e.move(GetSpeed(e.getEnemyType()), UP);
            }
            else{
                if(dir == LEFT) e.changeRotate(-90);
                else e.changeRotate(90);
                e.move(GetSpeed(e.getEnemyType()), DOWN);
            }
        }
        else{
            int newX = (int) (e.getX() + getSpeedAndWidth(LEFT, e.getEnemyType()));
            if(getTileType(newX, (int)e.getY()) == DIRT_TILE){
                if(dir == DOWN) e.changeRotate(90);
                else e.changeRotate(-90);
                e.move(GetSpeed(e.getEnemyType()), LEFT);
            }
            else{
                if(dir == DOWN) e.changeRotate(-90);
                else e.changeRotate(90);
                e.move(GetSpeed(e.getEnemyType()), RIGHT);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir){
            case RIGHT:
                if(xCord < 31) xCord++;
                break;
            case DOWN:
                if(yCord < 23) yCord++;
                break;
        }
        e.setPos(xCord * 32, yCord * 32);
    }

    private boolean isAtEnd(Enemy e) {
        if(e.getX() == end.getxCord() * 32 && e.getY() == end.getyCord() * 32){
            return true;
        }
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndHeight(int dir, int enemyType) {
        if(dir == UP) return -GetSpeed(enemyType);
        else if(dir == DOWN) return GetSpeed(enemyType) + 32;
        return 0;
    }

    private float getSpeedAndWidth(int dir, int enemyType) {
        if(dir == LEFT) return -GetSpeed(enemyType);
        else if(dir == RIGHT) return GetSpeed(enemyType) + 32;
        return 0;
    }
    public void spawnEnemy(int nextEnemy){
        addEnemy(nextEnemy);
    }

    public void addEnemy(int enemyTile){
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;
        switch (enemyTile){
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
        }
    }
    public void draw(Graphics g){
        for (Enemy e : enemies){
            if(e.isAlive()){
                drawEnemy(e, g, e.getRotate());
                drawHealthBar(e, g);
            }
        }
    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 10, getNewBarWidth(e), 3);
    }
    private int getNewBarWidth(Enemy e){
        return (int) (HPBarWidth * e.getHealthBarFloat());
    }
    private void drawEnemy(Enemy e, Graphics g, int rotate) {
        g.drawImage(getRotImg(enemyImgs[e.getEnemyType()], rotate), (int)e.getX() + 4, (int)e.getY() + 4, 24, 24, null);
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public int getAmountOfAliveEnemies() {
        int cnt = 0;
        for(Enemy e : enemies){
            if(e.isAlive()) ++cnt;
        }
        return cnt;
    }

    public void reward(int enemyType) {
        playing.reward(enemyType);
    }
}
