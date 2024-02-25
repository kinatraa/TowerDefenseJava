package managers;

import enemies.Enemy;
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

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float speed = 2f;
    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImgs = new BufferedImage[8];
        addEnemy(32*0, 32*6);
        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = getSpriteAtlas();
        enemyImgs[0] = atlas.getSubimage(0, 0, 32, 32);
        enemyImgs[1] = atlas.getSubimage(32, 0, 32, 32);
        enemyImgs[2] = atlas.getSubimage(2*32, 0, 32, 32);
        enemyImgs[3] = atlas.getSubimage(3*32, 0, 32, 32);
//        enemyImgs[4] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[5] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[6] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[7] = atlas.getSubimage(15*32, 10*32, 32, 32);
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
            if(isNextTileRoad(e)){

            }
        }
    }

    private boolean isNextTileRoad(Enemy e) {
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
        return false;
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
//            case LEFT:
//                if(xCord > 0) xCord--;
//                break;
//            case UP:
//                if(yCord > 0) yCord--;
//                break;
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

    public void addEnemy(int x, int y){
        enemies.add(new Enemy(x, y, 0, 0));
    }
    public void draw(Graphics g){
        for (Enemy e : enemies){
            drawEnemy(e, g);
        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[0], (int)e.getX(), (int)e.getY(), null);
    }
}
