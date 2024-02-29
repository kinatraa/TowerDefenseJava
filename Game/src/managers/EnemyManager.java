package managers;

import enemies.*;
import helpz.LoadSave;
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

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float speed = 2f;
    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImgs = new BufferedImage[8];
        addEnemy(32*0, 32*6, SOLDIER1);
        addEnemy(32*1, 32*6, SOLDIER2);
        addEnemy(32*2, 32*6, SOLDIER3);
        addEnemy(32*3, 32*6, SOLDIER4);
        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = getSpriteAtlas();
        for(int i = 0; i < 4; i++){
            enemyImgs[i] = atlas.getSubimage(i*32, 0, 32, 32);
        }
    }
    private static BufferedImage getSpriteAtlas() {
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
        for(Enemy e : enemies){
            updateEnemyMove(e);
        }
    }

    private void updateEnemyMove(Enemy e) {
        if(e.getLastDir() == -1){
            setNewDirectionAndMove(e);
        }

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir()));

        if(getTileType(newX, newY) == DIRT_TILE){
            //keep moving
            e.move(speed, e.getLastDir());
        }
        else if(isAtEnd(e)){
            //reached the end
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

        if(dir == LEFT || dir == RIGHT){
            int newY = (int) (e.getY() + getSpeedAndHeight(UP));
            if(getTileType((int)e.getX(), newY) == DIRT_TILE){
                e.move(speed, UP);
            }
            else e.move(speed, DOWN);
        }
        else{
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT));
            if(getTileType(newX, (int)e.getY()) == DIRT_TILE) e.move(speed, RIGHT);
            else e.move(speed, LEFT);
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
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    private float getSpeedAndHeight(int dir) {
        if(dir == UP) return -speed;
        else if(dir == DOWN) return speed + 32;
        return 0;
    }

    private float getSpeedAndWidth(int dir) {
        if(dir == LEFT) return -speed;
        else if(dir == RIGHT) return speed + 32;
        return 0;
    }

    public void addEnemy(int x, int y, int enemyTile){
        switch (enemyTile){
            case SOLDIER1:
                enemies.add(new Soldier1(x, y, 0));
                break;
            case SOLDIER2:
                enemies.add(new Soldier2(x, y, 0));
                break;
            case SOLDIER3:
                enemies.add(new Soldier3(x, y, 0));
                break;
            case SOLDIER4:
                enemies.add(new Soldier4(x, y, 0));
                break;
        }
    }
    public void draw(Graphics g){
        for (Enemy e : enemies){
            drawEnemy(e, g);
        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int)e.getX(), (int)e.getY(), null);
    }
}
